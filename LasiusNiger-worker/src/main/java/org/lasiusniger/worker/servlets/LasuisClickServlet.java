package org.lasiusniger.worker.servlets;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;
import org.lasiusniger.models.Configuration;
import org.lasiusniger.models.Guest;
import org.lasiusniger.models.Request;
import org.lasiusniger.models.Request.RequestType;
import org.lasiusniger.models.Zone;

/**
 *
 * @author ilyamirin
 */
@Log4j
@Singleton
public class LasuisClickServlet extends HttpServlet {

    private EntityManager em;
    private Configuration conf;
    private Gson gson;

    @Inject
    public LasuisClickServlet(EntityManager em, Configuration conf, Gson gson) {
        this.em = em;
        this.conf = conf;
        this.gson = gson;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        em.getTransaction().begin();

        Request request = new Request();
        request.setType(RequestType.CLICK);
        request.setRecievedAt(new Date());
        
        String zoneId = req.getParameter("zoneid");
        Zone zone = em.find(Zone.class, zoneId);
        if (zone == null) {
            //TODO:: throw new UnknownZoneException(zone);
        }
        request.setZone(zone);

        String guestId = req.getParameter("guestid");
        Guest guest = em.find(Guest.class, guestId);
        if (guest == null) {
            guest = new Guest();
            guest.setUUID(guestId);
            em.persist(guest);
        }
        request.setGuest(guest);

        em.getTransaction().commit();

        Map<String, Object> response = new HashMap<String, Object>();
        response.put("target", zone.getTarget());
                
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(response));
        out.flush();        
    }
}
