package pl.pop.interview.master.account;

class EmailInUseException extends RuntimeException {
    EmailInUseException(String message) {
        super(message);
    }
}
