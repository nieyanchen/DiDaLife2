package com.example.didalife;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuPageActivity extends AppCompatActivity {
    NavigationView navigationView;
    List<Fragment>fragments;
    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    MenuItem menuItem;
    Toolbar toolbar;

    String[]title={"A","B","C","D"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //显示标题栏

            //侧划菜单颜色显示

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu);
            navigationView=findViewById(R.id.navi1);
            navigationView.setItemIconTintList(null);
            toolbar=findViewById(R.id.toolbar2);
            toolbar.setLogo(R.mipmap.dida);
            toolbar.setTitle("DiDa Life");
            toolbar.setSubtitle("您的生活小助手");

            MenuPageActivity.this.setSupportActionBar(toolbar);




           //imageView11监听事件
            View view1 =navigationView.getHeaderView(0);
            ImageView imageView=view1.findViewById(R.id.imageView11);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MenuPageActivity.this,"请登录",Toast.LENGTH_SHORT).show();
                }
            });
            final TextView textView=view1.findViewById(R.id.textView7);
            //侧划功能监听
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    int id = menuItem.getItemId();
                    if (id == R.id.setting) {   //设置
                        startActivity(new Intent(MenuPageActivity.this,SettingActivity.class));
                    } else if (id == R.id.about) {     //关于
                        startActivity(new Intent(MenuPageActivity.this, AboutActivity.class));
                    } else if (id == R.id.style1) {     //主题

                    } else if (id == R.id.exit) {      //退出登陆
                        //退出登陆
                         AlertDialog.Builder builder = new AlertDialog.Builder(MenuPageActivity.this);
                                builder.setIcon(R.drawable.tishi);
                                builder.setTitle("提示");
                                builder.setMessage("真的要离开我了吗？QAQ");
                                builder.setPositiveButton("是嘞", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                                builder.setNegativeButton("算了",null);
                                builder.show();
                    }
                    return true;
                }

            });



            //菜单单击事件

            viewPager = findViewById(R.id.viewpager);
            bottomNavigationView=findViewById(R.id.bottom1);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.item1:
                            viewPager.setCurrentItem(0);
                            break;
                        case R.id.item2:
                            viewPager.setCurrentItem(1);
                            break;
                        case R.id.item3:
                            viewPager.setCurrentItem(2);
                            break;

                            default:break;

                    }
                    return true;
                }

            });
            //菜单与page同时滑动
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int position) {
                if (menuItem !=null){
                    menuItem.setChecked(false);
                }else {
                    menuItem=bottomNavigationView.getMenu().getItem(0);
                    menuItem.setChecked(true);
                }
                menuItem= bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });


            fragments= new ArrayList<>();
            fragments.add(new CostFragment());
            fragments.add(new LuckFragment());
            fragments.add(new NewsFragment());
            adapter pageAdapter = new adapter(getSupportFragmentManager(),fragments);
            viewPager.setAdapter(pageAdapter);
        }



    private class  adapter extends FragmentPagerAdapter {

        List<Fragment>fragments;

        public adapter(FragmentManager fm,List<Fragment> fragments) {
            super(fm);
            this.fragments=fragments;
        }

        @Override
        public Fragment getItem(int position) { ;
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }

    @Override
    //调用菜单2
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
    }


}
