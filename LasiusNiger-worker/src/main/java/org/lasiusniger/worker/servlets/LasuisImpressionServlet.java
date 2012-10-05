package org.lasiusniger.worker.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.lasiusniger.models.Request;
import org.lasiusniger.models.RequestType;
import org.lasiusniger.worker.dao.RequestDao;

/**
 *
 * @author ilyamirin
 */
@Singleton
public class LasuisImpressionServlet extends HttpServlet {
    
    private RequestDao requestDao;

    @Inject
    public LasuisImpressionServlet(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Request request = new Request();
        request.setRecievedAt(new Date());
        request.setZone(null);//req.getParameter("zoneid");
        request.setType(RequestType.IMPRESSION);
        requestDao.save(request);
        
        PrintWriter out = resp.getWriter();
        out.print(request.toString());
        out.flush();        
    }
}
