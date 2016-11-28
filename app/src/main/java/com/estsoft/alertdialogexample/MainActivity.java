package com.estsoft.alertdialogexample;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Activity Dialog도 알아보면 좋을듯 ?

    private boolean[] choices ={false,false,false,false,false,false};
    private int indexSingleChoiceSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dialogMessage( View view ) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);

//        builder.setTitle("알림");

//        builder.setIcon(R.mipmap.ic_launcher);
//        builder.setIcon(android.R.drawable.ic_dialog_alert); //android에 있는 기본 아이콘
//        builder.setMessage("Hello\nWorld");
//
//        builder.show();
        //위 내용을 아래와 같이 연결해서 가능하다.

        new AlertDialog.Builder(this).setTitle("알림").setIcon(android.R.drawable.ic_dialog_alert).setMessage("Hello").show();
    }

    public void dialogColseButton( View view ) {
        new AlertDialog.Builder(this).
                setTitle("알림").
                setIcon(android.R.drawable.ic_dialog_alert).
                setCancelable(true).
                setNegativeButton("close",null).
                setMessage("Hello").show();
    }

    public void dialogOKCancelButton( View view ) {
        new AlertDialog.Builder(this).
                setTitle("알림").
                setIcon(android.R.drawable.ic_dialog_alert).
                setCancelable(true).
                setNegativeButton("NO",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("setNegativeButton",""+which);    //위치값 알아보기
                    }
                }).
                setNeutralButton("Cacel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("setNeutralButton",""+which);
                    }
                }).
                setPositiveButton("YES",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("setPositiveButton",""+which);    //위치값 알아보기
                    }
                }).
                setMessage("Hello").show();
    }

    public void dialogList( View view ) {
        new AlertDialog.Builder(this).
                setTitle("선택하시오").
                setItems(R.array.lists, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("dialogList", ""+which); //index는 0부터 시작
                    }
                }).show();
    }

    public void dialogProgress( View vPew ) {
       /* new ProgressDialog.Builder(this).
                setTitle("처리중").
                setIcon(android.R.drawable.ic_lock_idle_charging).
                setMessage("잠시 기다려주세요").
                show();*/
        //위에 처럼 한번에쓰면 progress가 나오지 않는다. 그러므로 이것은 아래처럼 써야 한다.
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("처리중");
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setMessage("대기대기");
        dialog.show();
    }

    public void dialogSingleChoice( View view ) {
        new AlertDialog.Builder(this).
                setIcon(R.mipmap.ic_launcher).
                setTitle("SingChoice").
                setSingleChoiceItems(R.array.lists, indexSingleChoiceSelected, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("dialogSingleCHoice",""+which);
                        indexSingleChoiceSelected=which; //값을 유지함
                    }
                }).
                setPositiveButton("확인",null).
                show();
    }

    public void dialogMultipleChoice( View view ) {
        choices[0]=true;
        //이렇게 true를 해주면 처음 화면에 체크 된것으로 출력

        new AlertDialog.Builder(this).
                setIcon(R.mipmap.ic_launcher).
                setTitle("MultiChoice").
                setMultiChoiceItems(R.array.lists, choices , new DialogInterface.OnMultiChoiceClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Log.d("dialogMultipleChoice",""+which+":"+isChecked);
                        choices[which] = isChecked ;
                    }
                }).
                setPositiveButton("확인",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(boolean choice : MainActivity.this.choices){
                            Log.d("----->",""+choice);
                        }
                    }
                }).
                show();
    }

    public void dialogCustomLayout( View view ) {

        LayoutInflater inflater = getLayoutInflater();
        final View customView = inflater.inflate(R.layout.dialog_custom, null);

        new AlertDialog.Builder(this).
                setView(customView).
                setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText textEdit = (EditText) customView.findViewById(R.id.password);
                        Log.d("dialogCustomLayout", textEdit.getText().toString());
                    }
                }). //Dialog 확인 버튼 처리 방식

                show();
    }

    public void dialogCustomLayout2( View view ) {

        //다이알로그 내부 레이아웃 인플레이션
        LayoutInflater inflater = getLayoutInflater();
        final View customView = inflater.inflate(R.layout.dialog_custom, null);

        //화면에 다이알로그 표시
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.setView(customView).show();

        customView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)customView.findViewById(R.id.password);
                Log.d("dialogCustomLayout2", editText.getText().toString());
                alertDialog.cancel();
            }
        });

//        builder.setView(customView).show();
    }
}
