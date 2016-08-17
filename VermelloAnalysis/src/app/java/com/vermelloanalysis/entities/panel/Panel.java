package com.vermelloanalysis.entities.panel;

import com.vermelloanalysis.entities.chart.Chart;

/**
 * Created by User on 17/08/2016.
 */
public class Panel {

    @Id
    private Long id;

    @Index
    List<Chart> chartList;
}
