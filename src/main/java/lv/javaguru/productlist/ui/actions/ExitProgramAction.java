package lv.javaguru.productlist.ui.actions;

import org.springframework.stereotype.Component;

@Component
public class ExitProgramAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("Goodbye");
        System.exit(0);
    }

    @Override
    public int getMenuNumber() {
        return 0;
    }
}
