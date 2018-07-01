package org.schabi.newpipe.extractor;

import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.uih.UIHFactory;
import org.schabi.newpipe.extractor.uih.UIHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Info implements Serializable {

    private final int serviceId;
    /**
     * Id of this Info object <br>
     * e.g. Youtube:  https://www.youtube.com/watch?v=RER5qCTzZ7     &gt;    RER5qCTzZ7
     */
    private final String id;
    /**
     * Different than the {@link #originalUrl} in the sense that it <i>may</i> be set as a cleaned url.
     *
     * @see UIHandler#getUrl()
     * @see Extractor#getOriginalUrl()
     */
    private final String url;
    /**
     * The url used to start the extraction of this {@link Info} object.
     *
     * @see Extractor#getOriginalUrl()
     */
    private final String originalUrl;
    private final String name;

    private final List<Throwable> errors = new ArrayList<>();

    public void addError(Throwable throwable) {
        this.errors.add(throwable);
    }

    public void addAllErrors(Collection<Throwable> errors) {
        this.errors.addAll(errors);
    }

    public Info(int serviceId, String id, String url, String originalUrl, String name) {
        this.serviceId = serviceId;
        this.id = id;
        this.url = url;
        this.originalUrl = originalUrl;
        this.name = name;
    }

    public Info(int serviceId, UIHandler uiHandler, String name) {
        this(serviceId,
                uiHandler.getId(),
                uiHandler.getUrl(),
                uiHandler.getOriginalUrl(),
                name);
    }

    @Override
    public String toString() {
        final String ifDifferentString = !url.equals(originalUrl) ? " (originalUrl=\"" + originalUrl + "\")" : "";
        return getClass().getSimpleName() + "[url=\"" + url + "\"" + ifDifferentString + ", name=\"" + name + "\"]";
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getName() {
        return name;
    }

    public List<Throwable> getErrors() {
        return errors;
    }
}
