package map.model;

public class Information<T> extends Knowable {
    private T content;
    private final Class<T> contentClass;

    private Information(T content, Class<T> contentClass) {
        // private to control subclassing
        this.content = content;
        this.contentClass = contentClass;
    }

    public static Information<String> newText(String title, String content) {
        Information<String> information = new Information<String>(content, String.class);
        information.setTitle(title);
        return information;
    }

    /**
     * @return content of the information
     */
    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Class<T> getContentClass() {
        return contentClass;
    }
}
