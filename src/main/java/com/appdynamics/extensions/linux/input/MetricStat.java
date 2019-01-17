/*
 *
 *   Copyright 2018. AppDynamics LLC and its affiliates.
 *   All Rights Reserved.
 *   This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 *   The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 */

package com.appdynamics.extensions.linux.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MetricStat {
    @XmlAttribute
    private String url;
    @XmlAttribute
    private String name;
    @XmlAttribute(name = "filter-name")
    private String filterName;
    @XmlAttribute(name = "keyValueSeparator")
    private String keyValueSeparator;
    @XmlAttribute(name = "metric-type")
    private String metricType;
    @XmlAttribute
    public String children;
    @XmlElement(name = "metric")
    private MetricConfig[] metricConfig;
    @XmlElement(name = "stat")
    public MetricStat[] metricStats;


    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MetricConfig[] getMetricConfig() {
        return metricConfig;
    }

    public void setMetricConfig(MetricConfig[] metricConfig) {
        this.metricConfig = metricConfig;
    }

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MetricStat[] getMetricStats() {
        return metricStats;
    }

    public void setMetricStats(MetricStat[] metricStats) {
        this.metricStats = metricStats;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getKeyValueSeparator() {
        return keyValueSeparator;
    }

    public void setKeyValueSeparator(String keyValueSeparator) {
        this.keyValueSeparator = keyValueSeparator;
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class MetricStats {
        @XmlElement(name = "stat")
        private MetricStat[] metricStats;

        public MetricStat[] getMetricStats() {
            return metricStats;
        }

        public void setMetricStats(MetricStat[] metricStats) {
            this.metricStats = metricStats;
        }
    }
}