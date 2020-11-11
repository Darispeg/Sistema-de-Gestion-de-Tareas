package com.school.application.model;

public class TareaFile {
    
    private String documento;

    private String url;

    public TareaFile(String documento, String url) {
        this.documento = documento;
        this.url = url;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
