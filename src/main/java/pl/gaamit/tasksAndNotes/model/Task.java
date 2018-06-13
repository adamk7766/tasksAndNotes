package pl.gaamit.tasksAndNotes.model;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @OneToOne
    private User user;

    private boolean finished;

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isFinished() {

        return finished;
    }

    public String description() {

        return isFinished() ? "skończone " : "nieskończone";
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
