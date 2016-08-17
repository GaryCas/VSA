package com.vermelloanalysis.entities.chart;

import com.vermelloanalysis.entities.link.Link;

import java.util.List;

/**
 * Created by User on 17/08/2016.
 */
public class Chart {

    @Id
    private Long id;

    @Index
    private String alias;

    @Index
    private Link link;

    private ChartType chartType;

    private List<ChartLabel> chartLabelList;

    private enum ChartType{
        LINE, PIE
    }
}
