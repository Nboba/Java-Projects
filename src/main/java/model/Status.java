package model;

public enum Status {
    TODO,IN_PROGRESS,DONE,ABANDONED;
    public static Status getStatus(String status){
        return switch (status){
            case "ABANDONED" -> Status.ABANDONED;
            case "IN_PROGRESS" ->Status.IN_PROGRESS;
            case "DONE" ->Status.DONE;
            default -> Status.TODO;
        };
    }
}
