package controller;

import domain.*;
import view.InputView;
import view.OutputView;
import view.ResultView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    private static final String ALL_USER_RESULT = "all";

    private final InputView inputView;
    private final OutputView outputView;
    private final ResultView resultView;

    public Controller(final InputView inputView, final OutputView outputView, final ResultView resultView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.resultView = resultView;
    }

    public void run() {
        Users users = settingUsers();
        Width width = new Width(users.getCount() - 1);
        Height height = settingHeight();
        Ladder ladder = new Ladder(height, width, new RandomLadderGenerator());
        showLadder(users, ladder);
        Result result = playLadderGame(users, ladder);
        checkResult(users, result);
    }

    private Result playLadderGame(final Users users, final Ladder ladder) {
        Map<String, Integer> ladderGameResult = ladder.play(users);
        List<String> inputResult = settingResult(users.getCount());
        return new Result(inputResult, ladderGameResult);
    }

    private void showLadder(final Users users, final Ladder ladder) {
        outputView.printUsers(users);
        for (Line line : ladder.getLadder()) {
            outputView.printLadder(line);
        }
    }

    private void checkResult(final Users users, final Result userResult) {

        String inputResultWord = inputView.inputWantToKnowUser();
        if (wantToKnowAllResult(inputResultWord)) {
            return;
        }
        if (wantToKnowUserResult(users, userResult, inputResultWord)) return;
        resultView.printNonExistUser();
        checkResult(users, userResult);
    }

    private boolean wantToKnowAllResult(final String want) {
        if (want.equals(ALL_USER_RESULT)) {
            resultView.printAllResult();
            return true;
        }
        return false;
    }

    private boolean wantToKnowUserResult(final Users users, final Result userResult, final String inputResultWord) {
        if (users.contain(inputResultWord)) {
            resultView.printUserResult(userResult.getUserResult(inputResultWord));
            checkResult(users, userResult);
            return true;
        }
        return false;
    }

    private Users settingUsers() {
        try {
            List<String> userNames = inputView.inputUsername();
            return new Users(makeUsers(userNames));
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return settingUsers();
        }
    }

    private List<User> makeUsers(final List<String> userNames) {
        return userNames.stream()
                .map(User::new)
                .collect(Collectors.toList());
    }

    private Height settingHeight() {
        try {
            return new Height(inputView.inputLadderHeight());
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return settingHeight();
        }
    }

    private List<String> settingResult(final int userCount) {
        try {
            return inputView.inputResult(userCount);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return settingResult(userCount);
        }
    }
}
