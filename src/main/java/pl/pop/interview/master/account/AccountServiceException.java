package pl.pop.interview.master.account;

class AccountServiceException extends RuntimeException{
    AccountServiceException(String message) {
        super(message);
    }
}
