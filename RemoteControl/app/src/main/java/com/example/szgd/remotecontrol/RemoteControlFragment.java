package com.example.szgd.remotecontrol;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 */
public class RemoteControlFragment extends Fragment {

    private TextView mSelectedTextView;
    private TextView mWorkingTextView;
    public RemoteControlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_remote_control, container, false);
        mSelectedTextView= (TextView) v.findViewById(R.id.fragment_remote_control_selectedTextView);
        mWorkingTextView= (TextView) v.findViewById(R.id.fragment_remote_control_workingTextView);
        View.OnClickListener numberButtonListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView=(TextView)v;
                String working=mWorkingTextView.getText().toString();
                String text=textView.getText().toString();
                if(working.equals("0"))
                    mWorkingTextView.setText(text);
                else
                    mWorkingTextView.setText(working+text);
            }
        };
        TableLayout tableLayout= (TableLayout) v.findViewById(R.id.fragment_remote_control_tablelayout);
        int number=1;
        for(int i=2;i<tableLayout.getChildCount()-1;i++){
            TableRow row= (TableRow) tableLayout.getChildAt(i);
            for(int j=0;j<row.getChildCount();j++,number++){
                Button button= (Button) row.getChildAt(j);
                button.setText(""+number);
                button.setOnClickListener(numberButtonListener);
            }
        }
        TableRow bottomRow= (TableRow) tableLayout.getChildAt(tableLayout.getChildCount()-1);
        Button deleteButton= (Button) bottomRow.getChildAt(0);
        deleteButton.setText("Delete");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWorkingTextView.setText("0");
            }
        });
        /*Button zeroButton= (Button) v.findViewById(R.id.fragment_remote_control_zeroButton);
        zeroButton.setOnClickListener(numberButtonListener);
        Button oneButton= (Button) v.findViewById(R.id.fragment_remote_control_oneButton);
        oneButton.setOnClickListener(numberButtonListener);
        Button enterButton= (Button) v.findViewById(R.id.fragment_remote_control_enterButton);
        */
        Button zeroButton= (Button) bottomRow.getChildAt(1);
        zeroButton.setText("0");
        zeroButton.setOnClickListener(numberButtonListener);
        Button enterButton= (Button) bottomRow.getChildAt(2);
        enterButton.setText("Enter");
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence working=mWorkingTextView.getText();
                if(working.length()>0)
                    mSelectedTextView.setText(working);
                mWorkingTextView.setText("0");
            }
        });
        return v;
    }
}
