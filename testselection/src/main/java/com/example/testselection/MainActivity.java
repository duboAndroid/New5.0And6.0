package com.example.testselection;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private  ActionMode.Callback2 callback2 = new ActionMode.Callback2() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            menu.clear(); //清楚系统的菜单
            MenuInflater menuInflater = actionMode.getMenuInflater();
            menuInflater.inflate(R.menu.menu_main,menu); //创建自己的菜单
            return true;
        }
        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()){ //给菜单添加点击事件
                case R.id.copy:
                    Toast.makeText(MainActivity.this, "copy", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.cut:
                    Toast.makeText(MainActivity.this, "cut", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.past:
                    Toast.makeText(MainActivity.this, "past", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
              @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }
        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setCustomSelectionActionModeCallback(callback2);
    }
}
