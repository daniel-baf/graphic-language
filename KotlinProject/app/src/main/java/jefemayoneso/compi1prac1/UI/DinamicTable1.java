package jefemayoneso.compi1prac1.UI;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class DinamicTable1 {

    private TableLayout layout;
    private Context context;
    private String[] headers;
    private ArrayList<String[]> data;
    private TableRow tableRow;
    private TextView txtCell;
    private int indexC;
    private int indexR;

    public DinamicTable1(TableLayout layout, Context context) {
        this.layout = layout;
        this.context = context;
    }

    public void addHeader(String[] headers) {
        this.headers = headers;
    }

    public void addData(ArrayList<String[]>data) {
        this.data = data;
    }

    private void newRow() {
        tableRow = new TableRow(context);
    }

    private void newCell() {
        this.txtCell = new TextView(context);
        this.txtCell.setGravity(Gravity.CENTER);
        this.txtCell.setTextSize(25);
    }

    private void createHeader() {
        indexC = 0;
        newRow();
        while (indexC < headers.length) {
             newCell();
             txtCell.setText(headers[indexC++]);
             tableRow.addView(txtCell);
        }
        layout.addView(tableRow);
    }

    private void createDataTable() {
        String info;
        for(indexR = 1; indexR <= headers.length; indexR++) {
            newRow();
            for(indexC = 0; indexC <= headers.length; indexC++) {
                String[] cols = data.get(indexR - 1);
                info = (indexC<cols.length? cols[indexC]:"");
                txtCell.setText(info);
                tableRow.addView(txtCell,newTableRowParams());
            }
            layout.addView(tableRow);
        }
    }

    private TableRow.LayoutParams newTableRowParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight = 1;
        return params;
    }
}
