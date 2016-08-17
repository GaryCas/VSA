package com.vermelloanalysis.entities.link;

import com.vermelloanalysis.entities.connector.Connector;

import java.util.List;

/**
 * Created by User on 17/08/2016.
 */
public class Link {

    @Id
    private Long id;

    @Index
    private String alias;

    @Index
    private String filename;

    @Index
    private String hashtag;

    private List<Connector> connectorList;

    public Long getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public List<Connector> getConnectorList() {
        return connectorList;
    }

    public void setConnectorList(List<Connector> connectorList) {
        this.connectorList = connectorList;
    }
}
