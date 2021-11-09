package service;

public final class Responce implements ResponseStatus{
    public String message;
    public boolean ok;
    public Object data;

//    public static Responce setResponce(String message, boolean ok, Object data) {
//        message = message;
//        ok = ok;
//        data = data;

//    }

    public Responce(boolean ok, Object data) {
        if(ok) {
            this.message = resSucces;
        } else {
            this.message = resFailed;
        }
        this.ok = ok;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
