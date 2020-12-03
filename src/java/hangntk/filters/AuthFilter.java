/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.filters;

import hangntk.dtos.UserDTO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class AuthFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    List<String> guest;
    List<String> director;
    List<String> admin;
    List<String> cast;

    public AuthFilter() {
        guest = new ArrayList<>();
        guest.add("login.html");
        guest.add("error.jsp");
        guest.add("login");
        guest.add("login.action");

        director = new ArrayList<>();
        director.add("director.jsp");
        director.add("error.jsp");
        director.add("logout");
        director.add("logout.action");
        director.add("shoppingCart1.jsp");
        director.add("shoppingCart2.jsp");
        director.add("statisticalProp.jsp");
        director.add("viewCart1.jsp");
        director.add("viewCart2.jsp");
        director.add("loadListTribulationToAddCast");
        director.add("loadListTribulationToAddCast.action");
        director.add("loadListTribulationToAddProp");
        director.add("loadListTribulationToAddProp.action");
        director.add("loadListPartandCast");
        director.add("loadListPartandCast.action");
        director.add("addCastToPart");
        director.add("addCastToPart.action");
        director.add("deleteCastToPart");
        director.add("deleteCastToPart.action");
        director.add("saveCart1");
        director.add("saveCart1.action");
        director.add("addMore");
        director.add("addMore.action");
        director.add("addPropToTribu");
        director.add("addPropToTribu.action");
        director.add("deletePropToTribu");
        director.add("deletePropToTribu.action");
        director.add("addMoreProp");
        director.add("addMoreProp.action");
        director.add("saveCart2");
        director.add("saveCart2.action");
        director.add("statisticalProp");
        director.add("statisticalProp.action");
        director.add("statisticalPropStatus");
        director.add("statisticalPropStatus.action");
        director.add("viewNotiDirector.jsp");
        director.add("loadNotiDirector");
        director.add("loadNotiDirector.action");

        admin = new ArrayList<>();
        admin.add("admin.jsp");
        admin.add("error.jsp");
        admin.add("logout");
        admin.add("logout.action");
        admin.add("register.jsp");
        admin.add("registerProp.jsp");
        admin.add("registerTribulation.jsp");
        admin.add("editRecord");
        admin.add("editRecord.action");
        admin.add("update.jsp");
        admin.add("updateProp.jsp");
        admin.add("updateTribulation.jsp");
        admin.add("search");
        admin.add("search.action");
        admin.add("deleteRecord");
        admin.add("deleteRecord.action");
        admin.add("register");
        admin.add("register.action");
        admin.add("update");
        admin.add("update.action");
        admin.add("loadListDirector");
        admin.add("loadListDirector.action");
        admin.add("registerTribulation");
        admin.add("registerTribulation.action");
        admin.add("registerProp");
        admin.add("registerProp.action");
        admin.add("downFile");
        admin.add("downFile.action");
        admin.add("deleteRecordTribulation");
        admin.add("deleteRecordTribulation.action");
        admin.add("editTribulation");
        admin.add("editTribulation.action");
        admin.add("updateTribulation");
        admin.add("updateTribulation.action");
        admin.add("loadListDirectorUpdate");
        admin.add("loadListDirectorUpdate.action");
        admin.add("deleteRecordProp");
        admin.add("deleteRecordProp.action");
        admin.add("editProp");
        admin.add("editProp.action");
        admin.add("updateProp");
        admin.add("updateProp.action");

        cast = new ArrayList<>();
        cast.add("cast.jsp");
        cast.add("error.jsp");
        cast.add("logout");
        cast.add("logout.action");
        cast.add("loadCurrentTribu");
        cast.add("loadCurrentTribu.action");
        cast.add("showOldTribu");
        cast.add("showOldTribu.action");
        cast.add("viewPart.jsp");
        cast.add("viewPart");
        cast.add("viewPart.action");
        cast.add("downFile");
        cast.add("downFile.action");
        cast.add("viewNotiCast.jsp");
        cast.add("loadNotiCast");
        cast.add("loadNotiCast.action");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        int index = uri.lastIndexOf("/");
        String resource = uri.substring(index + 1);
        if (resource.contains(";")) {
            resource = resource.substring(0, resource.lastIndexOf(";"));
        }
        HttpSession session = req.getSession(false);

        if (uri.endsWith("jpg") || uri.endsWith("js") || uri.contains("css") || uri.endsWith("png")) {
            chain.doFilter(request, response);
            return;
        }
        if (session == null || session.getAttribute("INFO") == null) {
            if (guest.contains(resource)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("login.html");
            }
        } else {
            UserDTO dto = (UserDTO) session.getAttribute("INFO");
            if (dto.getRoleName().equals("Admin")) {
                if (admin.contains(resource)) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect("admin.jsp");
                }
            } else if (dto.getRoleName().equals("Director")) {
                if (director.contains(resource)) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect("director.jsp");
                }
            } else if (dto.getRoleName().equals("Cast")) {
                if (cast.contains(resource)) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect("cast.jsp");
                }
            }
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
