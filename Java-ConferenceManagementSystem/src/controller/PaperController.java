package controller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import model.Conference;
import model.Paper;
import model.UserAccount;

public class PaperController {

    @FXML
    private Text TitleText;

    @FXML
    private Text AuthorsText;

    @FXML
    private TextArea abstractText;

    @FXML
    private TextArea abstractText1;
    

    private Conference conference;

    public void setConference(Conference conference) {
        this.conference = conference;}
    
    public void setPaperDetails(Paper paper) {
        TitleText.setText(paper.getPaperTitle());
        AuthorsText.setText(getAuthorsAsString(paper.getAuthors()));
        abstractText.setText(paper.getAbstractPaper());
        abstractText.setEditable(false);
        abstractText1.setText(paper.getKeywords());
        abstractText1.setEditable(false);

        // Set other details as needed
    }

    private String getAuthorsAsString(List<UserAccount> authors) {
        StringBuilder builder = new StringBuilder();
        for (UserAccount author : authors) {
            builder.append(author.getUsername()).append(", ");
        }
        return builder.substring(0, builder.length() - 2);
    }

}
