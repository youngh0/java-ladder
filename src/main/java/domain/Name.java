package domain;

public class Name{
    private static final int USER_NAME_MIN_LENGTH = 1;
    private static final int USER_NAME_MAX_LENGTH = 5;
    private static final String INVALID_NAME_LENGTH_MESSAGE = "이름은 1~5 글자만 가능합니다.";
    private static final String INVALID_NAME_BLANK_MESSAGE = "이름은 공백으로만 이루어지면 안됩니다.";

    private static final String INVALID_NAME = "all";
    private static final String INVALID_NAME_MESSAGE = "이름은 all일 수 없습니다.";
    private final String name;

    public Name(final String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        validateNameLength(name);
        validateBlankName(name);
        validateNameIsAll(name);
    }

    private void validateNameIsAll(final String name) {
        if (name.equals(INVALID_NAME)) {
            throw new IllegalArgumentException(INVALID_NAME_MESSAGE);
        }
    }

    private void validateNameLength(final String name) {
        if (USER_NAME_MIN_LENGTH > name.length() || USER_NAME_MAX_LENGTH < name.length()) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH_MESSAGE);
        }
    }

    private void validateBlankName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(INVALID_NAME_BLANK_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
