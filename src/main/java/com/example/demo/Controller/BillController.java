package com.example.demo.Controller;

import com.example.demo.Entity.Bill;
import com.example.demo.Model.Helper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BillController implements Initializable {
    @FXML
    private Button Insert;

    @FXML
    private TextField BillID;

    @FXML
    private TextField BillNum;


    @FXML
    private TableView<Bill> BillTab;
    ObservableList<Bill>Billdata;
    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> num;

    @FXML
    private TableColumn<?, ?> datte;

    @FXML
    private TableColumn<?, ?> discount;

    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private TableColumn<?, ?> total;

    @FXML
    private TableColumn<?, ?> daybill;

    @FXML
    private TableColumn<?, ?> emp_id;

    @FXML
    private Label lid;

    @FXML
    private Label lname;

    @FXML
    private TextField Search;
    public void con() {
        Connection con = null;
        Statement state = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/store?user=root&password=");
            state = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        num.setCellValueFactory(new PropertyValueFactory<>("billNum"));
        discount.setCellValueFactory(new PropertyValueFactory<>("disc"));
        datte.setCellValueFactory(new PropertyValueFactory<>("date"));
        price.setCellValueFactory(new PropertyValueFactory<>("totPrice"));
        total.setCellValueFactory(new PropertyValueFactory<>("finalTot"));
        daybill.setCellValueFactory(new PropertyValueFactory<>("dayBillNum"));
        emp_id.setCellValueFactory(new PropertyValueFactory<>("empId"));
        fillTable();
        BillTab.setItems(Billdata);
        FilteredList<Bill> filteredList=new FilteredList<>(Billdata, b->true);
        Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(bill -> {
                if (newValue == null || newValue.trim().isEmpty()) {
                    return true;
                }
                String val = newValue.toLowerCase();
                return String.valueOf(bill.getId()).contains(val) ||
                        bill.getBillNum().toLowerCase().contains(val)|| String.valueOf(bill.getDisc()).contains(val)||
                        String.valueOf(bill.getFinalTot()).contains(val)|| String.valueOf(bill.getEmpId()).contains(val)
                        || String.valueOf(bill.getTotPrice()).contains(val) || bill.getDayBillNum().toLowerCase().contains(val)
                        ||  String.valueOf(bill.getDate()).contains(val);
            });
        });

        SortedList<Bill> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(BillTab.comparatorProperty());
        BillTab.setItems(sortedList);
    }
    public void fillTable() {
        try {
            ResultSet res = new Helper().fillTable("bill");
            Billdata = FXCollections.observableArrayList();
            while (res.next())
            {
                Bill p=new Bill();
                p.setId(res.getInt(1));
                p.setBillNum(res.getString(6));
                p.setTotPrice(res.getDouble(2));
                p.setDisc(res.getDouble(3));
                p.setFinalTot(res.getDouble(4));
                LocalDate localDate =p.getDate() ;
                p.setDate(res.getDate(5).toLocalDate());
                p.setDayBillNum(res.getString(7));
                p.setEmpId(res.getInt(8));
                Billdata.add(p);
            }
            BillTab.setItems(Billdata);
        }

        catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
