package models.responce;

public class Responce<T> implements ResponseStatus<T> {
    public String message;
    public boolean ok;
    public T data;

//    public static Responce setResponce(String message, boolean ok, Object data) {
//        message = message;
//        ok = ok;
//        data = data;
//    }

    public Responce(boolean ok, T data) {
        if(ok) {
            this.message = ResponseStatus.resSucces;
        } else {
            this.message = ResponseStatus.resFailed;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
