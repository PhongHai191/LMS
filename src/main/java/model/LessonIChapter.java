package model;

import java.util.List;

public class LessonIChapter {

    private Chapter chapter;
    private List<Lesson> list;

    public LessonIChapter() {
    }

    public LessonIChapter(Chapter chapter, List<Lesson> list) {
        this.chapter = chapter;
        this.list = list;
    }





    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public List<Lesson> getList() {
        return list;
    }

    public void setList(List<Lesson> list) {
        this.list = list;
    }


}
