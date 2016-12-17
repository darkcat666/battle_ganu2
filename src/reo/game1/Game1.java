package reo.game1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class Game1 extends Activity implements variable{

	// フィールド変数定義
	public static boolean AUTO_FLAG = false;
	public static int sleep_time = NORMAL_SLEEP;
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);		
		setContentView(new Game1Activity(this));
	}
	
// メニューアイテムの処理
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		MenuItem mi1 = menu.add(0,AUTO_ITEM,0,R.string.Auto);
		mi1.setIcon(android.R.drawable.star_big_on);
		MenuItem mi2 = menu.add(0,END_ITEM,1,R.string.string_exit);
		mi2.setIcon(android.R.drawable.ic_media_pause);
		MenuItem mi3 = menu.add(0,NORMAL_ITEM,2,R.string.normal);
		mi3.setIcon(android.R.drawable.star_big_off);
		return true;
	}
// Autoボタンの処理
	public void AutoButton(){
		AUTO_FLAG = true;
		sleep_time = AUTO_SLEEP;
	}
		
// Normalボタンの処理
	public void NormalButton(){
		AUTO_FLAG = false;
		sleep_time = NORMAL_SLEEP;
	}

// Menuアイテムの処理
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case AUTO_ITEM:
			AutoButton();
			break;
		case NORMAL_ITEM:
			NormalButton();
			break;
		case END_ITEM:
			// 終了します
		System.exit(0);
			break;
		}
		return true;
	}
}