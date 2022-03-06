package com.trss.bi.service.parser;

public class LynxParserOutput {
    protected String xhtml;
    protected String error;

    public LynxParserOutput(String xhtml) {
        this(xhtml, null);
    }

    public LynxParserOutput(String xhtml, String error) {
        this.xhtml = xhtml;
        this.error = error;
    }

    public String getXhtml() {
        return xhtml;
    }

    public void setXhtml(String xhtml) {
        this.xhtml = xhtml;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "LynxResponseContainer{" +
            ", xhtml='" + xhtml + '\'' +
            ", error='" + error + '\'' +
            '}';
    }
}
