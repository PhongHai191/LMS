package controller;

import dal.*;
import model.Account;
import model.Lesson;
import model.SystemSetting;
import utils.Paging;
import utils.ValidInput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
@WebServlet(name = "LessonList", urlPatterns = {"/llist"})
public class LessonList extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LessonList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LessonList at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if (request.getParameter("act") == null || request.getParameter("act").equals("")) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        } else {
            switch (request.getParameter("act")) {
                case "list": {
                    listLessonGet(request, response);
                    break;
                }
                case "add": {
                    try {
                        addLesson(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "upd": {
                    try {
                        updLesson(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "act": {
                    activateDeactivateLesson(request, response);
                    break;
                }
                case "view": {
                    viewLesson(request,response);
                    break;
                }
            }
        }
    }
    private void listLessonGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SystemSettingDAL stsd = new SystemSettingDAL();
        String order = sort(request, response);
        String sql = "";
        String moreC = searchByChapter(request, response);
        String moreS = searchByStatus(request, response);
        String moreT = searchByType(request, response);
        String moreN = searchByNameOrID(request, response);
        sql += "WHERE ";
        sql += moreC;
        sql += "AND";
        sql += moreS;
        sql += "AND";
        sql += moreT;
        sql += "AND";
        sql += moreN;
        Paging p = new Paging();
        int numPerPage = 5;
        String page_raw = request.getParameter("page");
        int page = 1;
        if (page_raw == null || page_raw.equals("")) {
            page = 1;
        } else {
            try {
                page = Integer.parseInt(page_raw);
            } catch (Exception e) {
                System.out.println(e);
                response.sendRedirect("view/error.jsp");
            }
        }
        List<Lesson> list = ssd.getLessonBySearch(sql, order);
        request.setAttribute("chapter", ssd.getAllChapter());
        request.setAttribute("type", stsd.getAllLessonType());
        if (!list.isEmpty()) {
            request.setAttribute("totalEntity", list.size());
            request.setAttribute("totalPage", p.getTotalPage(page, list.size(), numPerPage));
            list = pagination(list, page, numPerPage);
            request.setAttribute("size", list.size());
            request.setAttribute("list", 1);
            request.setAttribute("listL", list);
            request.setAttribute("page", page);
        } else {
            request.setAttribute("ms", "Don't have any lessons");
        }
        request.getRequestDispatcher("views/LessonList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Instant instant = Instant.now();
        Timestamp now = Timestamp.from(instant);
        HttpSession session = request.getSession();
        Account a = new Account(1,"bangthhe170871","bangthhe170871@fpt.edu.vn","1",3,1,"Tran Hai Bang","0394672294", Date.valueOf(LocalDate.of(2024,02,01)),null,new SystemSetting(1,"User","UserRole",1,1));
        System.out.println(request.getParameter("act"));
        if (request.getParameter("act").equals("list")) {
            BufferedReader reader = request.getReader();
            String text = reader.readLine();
            while (text != null) {
                String id_raw = "";
                for (int i = 0; i < text.length(); i++) {
                    if (text.substring(i, i + 3).equals("id=")) {
                        id_raw = text.substring(i + 3);
                        break;
                    }
                }
                int id = Integer.parseInt(id_raw);
                SubjectSettingDAL ssd = new SubjectSettingDAL();
                if (ssd.getLessonByID(id).getStatus() == 1) {
                    ssd.actLesson(0, id);
                } else {
                    ssd.actLesson(1, id);
                }
                text = reader.readLine();
            }
        } else if (request.getParameter("act") != null && request.getParameter("act").equals("add")) {
            String subject_raw = request.getParameter("subject");
            String chapter_raw = request.getParameter("chapter");
            String name = request.getParameter("name");
            String order_raw = request.getParameter("order");
            String status_raw = request.getParameter("status");
            String class_raw = request.getParameter("class");
            String description = request.getParameter("summerNoteText");
            int subjectID = 0, chapterID = 0, order = 0, status = 0, classID = 0;
            SystemSettingDAL sstd = new SystemSettingDAL();
            try {
                subjectID = Integer.parseInt(subject_raw);
                chapterID = Integer.parseInt(chapter_raw);
                order = Integer.parseInt(order_raw);
                status = Integer.parseInt(status_raw);
                classID = Integer.parseInt(class_raw);
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                if (request.getParameter("type").equals("video")) {
                    String video = request.getParameter("link");
                    SubjectDAL sd = new SubjectDAL();
                    ClassDAL cd = new ClassDAL();
                    SubjectSettingDAL ssd = new SubjectSettingDAL();
                    Lesson l = new Lesson();
                    l.setS(sd.getSubjectByID(subjectID));
                    l.setChapter(ssd.getChapterByID(chapterID));
                    l.setLessonName(name);
                    l.setDisplayOrder(order);
                    l.setStatus(status);
                    if (classID != 0) {
                        l.setCls(cd.getClassByID(classID));
                    }
                    l.setLessonType(sstd.getSetting("Video"));
                    l.setAttatchedFile("");
                    l.setVideoLink(video);
                    l.setDescription(description);
                    l.setCreatedAt(now);
                    l.setCreatedBy(a);
                    ssd.addLessonVideo(l);
                    listLessonGet(l, request, response);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (request.getParameter("act").equals("upd")){
            String page = request.getParameter("page");
            String id_raw = request.getParameter("id");
            String subject_raw = request.getParameter("subject");
            String chapter_raw = request.getParameter("chapter");
            String name = request.getParameter("name");
            String order_raw = request.getParameter("order");
            String status_raw = request.getParameter("status");
            String class_raw = request.getParameter("class");
            String description = request.getParameter("summerNoteText");
            int subjectID = 0, chapterID = 0, order = 0, status = 0, classID = 0, id = 0;
            SystemSettingDAL sstd = new SystemSettingDAL();
            try {
                id = Integer.parseInt(id_raw);
                subjectID = Integer.parseInt(subject_raw);
                chapterID = Integer.parseInt(chapter_raw);
                order = Integer.parseInt(order_raw);
                status = Integer.parseInt(status_raw);
                classID = Integer.parseInt(class_raw);
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                if (request.getParameter("type").equals("video")) {
                    String video = request.getParameter("link");
                    SubjectDAL sd = new SubjectDAL();
                    ClassDAL cd = new ClassDAL();
                    SubjectSettingDAL ssd = new SubjectSettingDAL();
                    Account createdBy = ssd.getLessonByID(id).getCreatedBy();
                    Timestamp createdAt = ssd.getLessonByID(id).getCreatedAt();
                    Lesson l = new Lesson();
                    l.setCreatedAt(createdAt);
                    l.setCreatedBy(createdBy);
                    l.setLessonID(id);
                    l.setS(sd.getSubjectByID(subjectID));
                    l.setChapter(ssd.getChapterByID(chapterID));
                    l.setLessonName(name);
                    l.setDisplayOrder(order);
                    l.setStatus(status);
                    if (classID != 0) {
                        l.setCls(cd.getClassByID(classID));
                    }
                    l.setLessonType(sstd.getSetting("Video"));
                    l.setAttatchedFile("");
                    l.setVideoLink(video);
                    l.setDescription(description);
                    l.setUpdatedAt(now);
                    l.setUpdatedBy(a);
                    ssd.updLessonVideo(l);
                    listLessonGet(page, request, response);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void listLessonGet(String page1, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("kdoa");
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SystemSettingDAL stsd = new SystemSettingDAL();
        String order = "";
        String sql = "";
        int page = Integer.parseInt(page1);
        Paging p = new Paging();
        int numPerPage = 5;

        List<Lesson> list = ssd.getLessonBySearch(sql, order);
        request.setAttribute("chapter", ssd.getAllChapter());
        request.setAttribute("type", stsd.getAllLessonType());
        if (!list.isEmpty()) {
            request.setAttribute("totalEntity", list.size());
            request.setAttribute("totalPage", p.getTotalPage(page, list.size(), numPerPage));
            list = pagination(list, page, numPerPage);
            request.setAttribute("size", list.size());
            request.setAttribute("list", 1);
            request.setAttribute("listL", list);
            request.setAttribute("page", page);
            request.setAttribute("msg", "Update successfully");
        } else {
            request.setAttribute("ms", "Don't have any lessons");
        }
        request.getRequestDispatcher("views/LessonList.jsp").forward(request, response);
    }
    private void listLessonGet(Lesson l, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SystemSettingDAL stsd = new SystemSettingDAL();
        String order = "";
        String sql = "";
        Paging p = new Paging();
        int numPerPage = 5;
        String page_raw = request.getParameter("page");
        List<Lesson> list = ssd.getLessonBySearch(sql, order);
        int page = (list.size() % numPerPage == 0) ? (list.size() / numPerPage) : (list.size() / numPerPage + 1);
        request.setAttribute("chapter", ssd.getAllChapter());
        request.setAttribute("type", stsd.getAllLessonType());
        if (!list.isEmpty()) {
            request.setAttribute("totalEntity", list.size());
            request.setAttribute("totalPage", p.getTotalPage(page, list.size(), numPerPage));
            list = pagination(list, page, numPerPage);
            request.setAttribute("size", list.size());
            request.setAttribute("list", 1);
            request.setAttribute("listL", list);
            request.setAttribute("page", page);
            String msg = "Add successfully";
            request.setAttribute("msg", msg);
        } else {
            request.setAttribute("ms", "Don't have any lessons");
        }
        request.getRequestDispatcher("views/LessonList.jsp").forward(request, response);
    }
    public List<Lesson> pagination(List<Lesson> list, int page, int numPerPage) {
        Paging p = new Paging();
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        int start = p.getStart(page, list.size(), numPerPage);
        int end = p.getEnd(page, list.size(), numPerPage);
        list = ssd.getLessonByPage(start, end, list);
        return list;
    }
    private String sort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String o = "";
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        if (request.getParameter("sort") == null || request.getParameter("sort").equals("")
                || request.getParameter("order") == null || request.getParameter("order").equals("")) {
            o = "ORDER BY display_order, lesson_id";
        } else {
            if (!request.getParameter("sort").equals("ID") && !request.getParameter("sort").equals("Name")
                    && !request.getParameter("sort").equals("Chapter") && !request.getParameter("sort").equals("Type")
                    && !request.getParameter("sort").equals("Order") && !request.getParameter("sort").equals("status")) {
                o = "ORDER BY display_order, lesson_id";
            } else {
                int order = 0;
                try {
                    order = Integer.parseInt(request.getParameter("order"));
                    if (order != 0 && order != 1) {
                        throw new Exception("L");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    request.getRequestDispatcher("view/error.jsp").forward(request, response);
                }
                if (order == 0) {
                    request.setAttribute("sort", request.getParameter("sort"));
                    request.setAttribute("order", 0);
                    o = ssd.getSortedLessonDesc(request.getParameter("sort"));
                } else {
                    request.setAttribute("sort", request.getParameter("sort"));
                    request.setAttribute("order", 1);
                    o = ssd.getSortedLessonAsc(request.getParameter("sort"));
                }
            }
        }
        return o;
    }
    private String searchByChapter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        String more = " 1 = 1 ";
        String id_raw = request.getParameter("chapter");
        if (request.getParameter("chapter") == null
                || request.getParameter("chapter").equals("")) {
            more = " 1 = 1 ";
        } else {
            try {
                id = Integer.parseInt(id_raw);

            } catch (Exception e) {
                System.out.println(e);
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            }
            request.setAttribute("c", id);
            if (id == 0) {
                more = " 1 = 1 ";
            } else {
                more = " chapter_id = " + id + " ";
            }
        }
        return more;
    }
    private String searchByStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        String more = " 1 = 1 ";
        if (request.getParameter("status") == null
                || request.getParameter("status").equals("")) {
            request.setAttribute("s", -1);
            more = " 1 = 1 ";
        } else {
            int status = 0;
            try {
                status = Integer.parseInt(request.getParameter("status"));
                if (status != 1 && status != 0 && status != -1) {
                    throw new Exception("L");
                }
            } catch (Exception e) {
                System.out.println(e);
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            }
            request.setAttribute("s", status);
            if (status == -1) {
                more = " 1 = 1 ";
            } else {
                more = " status = " + status + " ";
            }
        }
        return more;
    }
    private String searchByType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        String more = " 1 = 1 ";
        String id_raw = request.getParameter("type");
        if (request.getParameter("type") == null
                || request.getParameter("type").equals("")) {
            more = " 1 = 1 ";
        } else {
            try {
                id = Integer.parseInt(id_raw);

            } catch (Exception e) {
                System.out.println(e);
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            }
            request.setAttribute("t", id);
            if (id == 0) {
                more = " 1 = 1 ";
            } else {
                more = " lesson_type = " + id + " ";
            }
        }
        return more;
    }
    private String searchByNameOrID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = "", more = "";
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        if (request.getParameter("search") == null
                || request.getParameter("search").equals("")) {
            request.setAttribute("search", key);
            more = " 1 = 1 ";
        } else {
            key = request.getParameter("search");
            request.setAttribute("search", key);
            more = " lesson_name LIKE '%" + key + "%' ";
        }
        return more;
    }
    private void addLesson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SubjectDAL sd = new SubjectDAL();
        ClassDAL cd = new ClassDAL();
        request.setAttribute("listS", sd.getAllSubjects());
        request.setAttribute("listC", ssd.getAllChapterBySubject(1));
        request.setAttribute("listCls", cd.getAllClasses());
        request.setAttribute("add", 1);
        request.getRequestDispatcher("views/NewLesson.jsp").forward(request, response);
    }
    private void updLesson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setAttribute("upd", 1);
        String id_raw = request.getParameter("id");
        String page_raw = request.getParameter("page");
        int id = 0;
        try {
            id = Integer.parseInt(id_raw);
        } catch (Exception e) {
            System.out.println(e);
        }
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        Lesson l = ssd.getLessonByID(id);
        SubjectDAL sd = new SubjectDAL();
        ClassDAL cd = new ClassDAL();
        request.setAttribute("l", l);
        request.setAttribute("id", id);
        request.setAttribute("page", page_raw);
        request.setAttribute("listS", sd.getAllSubjects());
        request.setAttribute("listC", ssd.getAllChapterBySubject(1));
        request.setAttribute("listCls", cd.getAllClasses());
        request.getRequestDispatcher("views/LessonDetail.jsp").forward(request, response);
    }
    private void viewLesson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_raw = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(id_raw);
        } catch (Exception e) {
            System.out.println(e);
        }
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        AsmDAL ad = new AsmDAL();
        Lesson l = ssd.getLessonByID(id);
        request.setAttribute("lesson", l);
        request.setAttribute("chapter", l.getChapter());
        if (ssd.getLessonByID(l.getLessonID()).getVideoLink() != null) {
            request.setAttribute("video", ssd.getLessonByID(l.getLessonID()).getVideoLink());
        }
        if (ssd.getLessonByID(l.getLessonID()).getVideoLink() == null) {
            if(ssd.getLessonByID(l.getLessonID()).getVideoLink() == null){
                request.setAttribute("asm", ad.getAsmByID(ssd.getLessonByID(l.getLessonID()).getAsm().getAsmID()));
            }
        }
        request.getRequestDispatcher("views/ViewLesson.jsp").forward(request, response);
    }

    private void activateDeactivateLesson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ValidInput v = new ValidInput();
        String id_raw = request.getParameter("id");
        String page_raw = request.getParameter("page");
        int id = v.validInt(id_raw, request, response);
        int page = v.validInt(page_raw, request, response);
        System.out.println(id + " " + page);
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        if (ssd.getLessonByID(id) != null) {
            if (ssd.getLessonByID(id).getStatus() == 1)  {
                ssd.actLesson(0, id);
            } else {
                ssd.actLesson(1, id);
            }
            //   listLessonPage(page, request, response);
        }

    }

}

