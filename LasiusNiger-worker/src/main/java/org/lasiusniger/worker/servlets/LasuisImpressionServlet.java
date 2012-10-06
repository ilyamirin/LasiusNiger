package org.lasiusniger.worker.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.lasiusniger.models.Request;
import org.lasiusniger.models.RequestType;

/**
 *
 * @author ilyamirin
 */
@Singleton
public class LasuisImpressionServlet extends HttpServlet {

    private EntityManager em;

    @Inject
    public LasuisImpressionServlet(EntityManager em) {
        this.em = em;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        em.getTransaction().begin();

        //находим зону с зависимостями и переадем ее в реквест
        //вызываем стратегию для реквеста
        //определяем баннер
        //отдаем баннер        
        Request request = new Request();
        request.setRecievedAt(new Date());
        request.setZone(null);//req.getParameter("zoneid");
        request.setType(RequestType.IMPRESSION);
        em.persist(request);

        em.getTransaction().commit();

        PrintWriter out = resp.getWriter();
        out.print(request.toString());
        out.flush();
    }
}
