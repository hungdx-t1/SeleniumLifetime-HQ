package pika.hungt1.dx;

public class LifetimeException extends Exception {

    @SuppressWarnings("unused")
    public enum CodeInject {
        TOKEN_1("File is not overriten!"),
        TOKEN_2("Consumer not found");

        private final String context;

        CodeInject(String context) {
            this.context = context;
        }

        public String getContext() {
            return context;
        }
    }

    public LifetimeException(CodeInject codeInject) {
        super(context(codeInject));
    }

    private static String context(CodeInject codeInject) {
        String errorCode = codeInject.toString().substring(6); // bỏ cụm "TOKEN_", chỉ lấy con số
        return "[Mã lỗi: %s] (%s)".formatted(errorCode, codeInject.getContext());
    }
}
