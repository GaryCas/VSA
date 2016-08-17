package com.vermelloanalysis.entities.connector;

import java.util.List;

/**
 * Created by User on 17/08/2016.
 */
public class Connector {

    @Id
    private Long id;

    @Index
    private String alias;

    private String token;

    @Index
    private FeedSource source;

    // Tokens

    public enum FeedSource {
        TWITTER
    }

}
