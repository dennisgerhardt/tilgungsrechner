package com.mycompany.tilgungsrechner.controller;

import com.mycompany.tilgungsrechner.data.Schedule;
import com.mycompany.tilgungsrechner.data.ScheduleItem;
import com.mycompany.tilgungsrechner.service.ISceneManager;
import com.mycompany.tilgungsrechner.service.IStorage;
import com.mycompany.tilgungsrechner.service.ServiceResolver;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultViewerController implements Initializable {

    private final ISceneManager sceneManager;
    private final IStorage storage;

    @FXML
    private Button backButton;

    @FXML
    private TableView<ScheduleItem> repaymentSchedule;

    @FXML
    private TableColumn<ScheduleItem, String> colDate, colDebt, colInterest, colRepayment, colFixedRate;

    public ResultViewerController() {
        sceneManager = ServiceResolver.resolve(ISceneManager.class);
        storage = ServiceResolver.resolve(IStorage.class);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButton.setOnAction(this::backExecuted);

        Schedule schedule = (Schedule) storage.get("meine.werte");
        ObservableList<ScheduleItem> items = FXCollections.observableArrayList (
                schedule.getScheduleItems()
        );
        repaymentSchedule.setItems(items);

        colDate.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getRateDate()));
        colDebt.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getDebt()));
        colInterest.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getDebitAmount()));
        colRepayment.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getRepaymentRate()));
        colFixedRate.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getFixedRate()));
    }

    protected void backExecuted(ActionEvent actionEvent) {
        storage.del("meine.werte");
        sceneManager.navigateTo(ISceneManager.INPUT_FORM, actionEvent);
    }
}