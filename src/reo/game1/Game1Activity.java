package reo.game1;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Game1Activity extends SurfaceView implements variable, OnCompletionListener, Runnable, SurfaceHolder.Callback{

// フィールド変数定数
	public Game1 game1;
	private static boolean ONE_TIME_SKIP = true;
	private static int PLACE1 = 1;
	private static int PLACE2 = 2;
	private static int PLACE3 = 3;
	private static int PLACE4 = 4;
	private int end = 0;
	private static int scene = 0;
	private int init = -1;
	private int key = 0;
	private final static int
		S_INITIALIZE=0,
		S_APPEAR1=1,
		S_APPEAR2=2,
		S_APPEAR3=3,
		S_APPEAR4=4,
		S_BETTING=5,
		S_BATTLE1=6,
		S_BATTLE2=7,
		S_ENDING1=8,
		S_ENDING2=9;
	
	private final static int
		K_NONE=10,
		K_RIGHT=11,
		K_LEFT=12,
		K_SELECT=13;
	
	public int width;
	public int height;
	private Graphics g;
	private SurfaceHolder holder;
	private Bitmap[] bitmap = new Bitmap[10];
	private static int ene1;
	private static int ene2;
	private static int ene3;
	private static int ene4;
	private static int aimingEnemy;
	private static int aimedEnemy;
	private static field_status aiming_flag;
	private static field_status aimed_flag;
	private static field_status Enemy1;
	private static field_status Enemy2;	
	private static field_status Enemy3;
	private static field_status Enemy4;
	private static field_status choose;
	private static int dead_count = 0;
	private MediaPlayer player;
	private Thread thread;
	private static int damageStep1 = 0;
	public static boolean avoid_flag;
	private static final int damage_least = 1;
	public static boolean end_flag = false;
	private static field_status death1;
	private static field_status death2;
	private static field_status death3;
	private static boolean lock_flag = false;
	
	static Random rand = new Random();
	field_status [] fs = new field_status[9];

// コンストラクタ
	public Game1Activity(Context context) {
		super(context);
		this.player = null;		
		
		Resources r = getResources();
		bitmap[0] = BitmapFactory.decodeResource(r,R.drawable.enemy0);
		bitmap[1] = BitmapFactory.decodeResource(r,R.drawable.enemy1);
		bitmap[2] = BitmapFactory.decodeResource(r,R.drawable.enemy2);
		bitmap[3] = BitmapFactory.decodeResource(r,R.drawable.enemy3);
		bitmap[4] = BitmapFactory.decodeResource(r,R.drawable.enemy4);
		bitmap[5] = BitmapFactory.decodeResource(r,R.drawable.enemy5);
		bitmap[6] = BitmapFactory.decodeResource(r,R.drawable.enemy6);
		bitmap[7] = BitmapFactory.decodeResource(r,R.drawable.enemy7);
		bitmap[8] = BitmapFactory.decodeResource(r,R.drawable.enemy8);
		bitmap[9] = BitmapFactory.decodeResource(r,R.drawable.dummy);

		
		// onDraw()を呼ばれるようにする関数
		setWillNotDraw(false);
		
		holder = getHolder();
		holder.addCallback(this);
		holder.setFixedSize(getWidth(),getHeight());
		g = new Graphics(holder, context);
		
	}
	
// サーフェイスの生成
	public void surfaceCreated(SurfaceHolder holder){
		thread = new Thread(this);
		thread.start();
	}
	
// サーフェイスの変更
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h){
		
	}
	
// サーフェイスの破棄
	public void surfaceDestroyed(SurfaceHolder holder){
		thread = null;
	}
	
// メッセージ表示の白い枠の描画
	public void drawMessageFrame(){
		g.settingColor(Color.WHITE);
		Graphics.paint.setStyle(Paint.Style.STROKE);		
		Graphics.paint.setStrokeWidth(4);
		g.drawRect(5,height/2+5,width-5,height-5);
	}
	
// 一番左の敵の描画。HPがなくなり次第、枠を残して消滅する。
	public void drawEnemyA(){
		g.settingColor(Color.RED);		
		g.drawRect(0,0,width / 4,height/2);
		if (Enemy1.getHP() > 0){
			g.drawBitmap(bitmap[ene1],0,0,width / 4,(height/2));
		}
		else if (Enemy1.getHP() == 0){
			g.drawBitmap(bitmap[9],0,0,width / 4,(height/2));
		}
	}
// 左から二番目の敵の描画。HPがなくなり次第、枠を残して消滅する。	
	public void drawEnemyB(){
		g.settingColor(Color.GREEN);
		g.drawRect(width / 4 ,0 , width / 4 * 2,(height/2));
		if (Enemy2.getHP() > 0){
			g.drawBitmap(bitmap[ene2],width / 4 ,0 , width / 4 * 2,(height/2));
		}
		else if (Enemy2.getHP() == 0){
			g.drawBitmap(bitmap[9],width / 4 ,0 , width / 4 * 2,(height/2));
		}
	}
// 左から三番目の敵の描画。HPがなくなり次第、枠を残して消滅する。
	public void drawEnemyC(){
		g.settingColor(Color.YELLOW);
		g.drawRect(width / 4 * 2, 0, width / 4 * 3,(height/2));
		if (Enemy3.getHP() > 0){
			g.drawBitmap(bitmap[ene3],width / 4 * 2, 0, width / 4 * 3,(height/2));
		}
		else if (Enemy3.getHP() == 0){
			g.drawBitmap(bitmap[9],width / 4 * 2, 0, width / 4 * 3,(height/2));
		}
	}
// 一番右の敵の描画。HPがなくなり次第、枠を残して消滅する。
	public void drawEnemyD(){
		g.settingColor(Color.BLUE);
		g.drawRect(width / 4 * 3, 0,width / 4 * 4 ,(height/2));
		if (Enemy4.getHP() > 0){
			g.drawBitmap(bitmap[ene4],width / 4 * 3, 0,width / 4 * 4 ,(height/2));
		}
		else if (Enemy4.getHP() == 0){
			g.drawBitmap(bitmap[9],width / 4 * 3, 0,width / 4 * 4 ,(height/2));
		}
	}
	
// 敵を描画する関数をまとめる関数
	public void drawEnemys(){
		drawEnemyA();
		drawEnemyB();
		drawEnemyC();
		drawEnemyD();
		Graphics.paint.setStrokeWidth(1);
	}

// 敵の初期化・・・どの敵が何番目にくるかをランダムで計算
	public void InitEnemyMake(){
		
		if (end_flag == false){
			// 実行時エラー回避
			Looper.prepare();
		}
		
		setEne1(rand.nextInt(9));
		setEne2(rand.nextInt(9));
		while (ene1 == ene2){
			setEne2(rand.nextInt(9));
		}
		setEne3(rand.nextInt(9));
		while (ene1 == ene3 || ene2 == ene3){
			setEne3(rand.nextInt(9));
		}
		setEne4(rand.nextInt(9));
		while (ene1 == ene4 || ene2 == ene4 || ene3 == ene4){
			setEne4(rand.nextInt(9));
		}
		
		enemy1 enem1 = new enemy1(g.holder, getContext());
		enemy2 enem2 = new enemy2(g.holder, getContext());
		enemy3 enem3 = new enemy3(g.holder, getContext());
		enemy4 enem4 = new enemy4(g.holder, getContext());
		enemy5 enem5 = new enemy5(g.holder, getContext());
		enemy6 enem6 = new enemy6(g.holder, getContext());
		enemy7 enem7 = new enemy7(g.holder, getContext());
		enemy8 enem8 = new enemy8(g.holder, getContext());
		enemy9 enem9 = new enemy9(g.holder, getContext());		
		
		field_status [] fs = {enem1,enem2,enem3,enem4,enem5,enem6,enem7,enem8,enem9,death1,
				death2,death3};
		
		setEnemy1(fs[ene1]);		
		setEnemy2(fs[ene2]);
		setEnemy3(fs[ene3]);
		setEnemy4(fs[ene4]);
		
	}
	
// 変数betのget関数
	public field_status getchoose(){
		return choose;
	}
	
// 通常攻撃か特殊技能かを分岐させる処理
	public void ChooseAttack(){
		int choose = rand.nextInt(2);
		if (dead_count >= 3){
			scene = S_ENDING1;
		}
		else if (choose == 0){
			Attack();
		}
		else if (choose == 1){
			magicAttack();
		}
	}
	
// 通常攻撃をしたときの処理
	public static void Attack(){
		if (dead_count < 3){
				dont_aimed_dead();
				dont_aim_dead();
				setAimedEnemy(rand.nextInt(4));
			
				switch (aimedEnemy){
				case 0: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy1)));
						setAimed_flag(Enemy1);
						break;
				case 1: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy2)));
						setAimed_flag(Enemy2);
						break;
				case 2: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy3)));
						setAimed_flag(Enemy3);
						break;
				case 3: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy4)));
						setAimed_flag(Enemy4);
						break;
				}
				aimedEnemy = rand.nextInt(4);
				switch (aimedEnemy){
				case 0: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy1)));
						setAimed_flag(Enemy1);
						Enemy1.setMagic_flag(true);
						break;
				case 1: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy2)));
						setAimed_flag(Enemy2);
						Enemy2.setMagic_flag(true);
						break;
				case 2: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy3)));
						setAimed_flag(Enemy3);
						Enemy3.setMagic_flag(true);
						break;
				case 3: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy4)));
						setAimed_flag(Enemy4);
						Enemy4.setMagic_flag(true);
						break;
				}
				switch (aimedEnemy){
				case 0: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy1)));
						setAimed_flag(Enemy1);
						break;
				case 1: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy2)));
						setAimed_flag(Enemy2);
						break;
				case 2: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy3)));
						setAimed_flag(Enemy3);
						break;
				case 3: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy4)));
						setAimed_flag(Enemy4);
						break;
				}
				dont_aim_dead();
				dont_aimed_dead();
				
				switch (aimedEnemy){
				case 0: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy1)));
						setAimed_flag(Enemy1);
						break;
				case 1: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy2)));
						setAimed_flag(Enemy2);
						break;
				case 2: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy3)));
						setAimed_flag(Enemy3);
						break;
				case 3: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy4)));
						setAimed_flag(Enemy4);
						break;
				}
				dont_aim_dead();
				dont_aimed_dead();
				
			int attackerLine = rand.nextInt(3);
			int defenderLine = rand.nextInt(2);

			setEnemyColor(getEnemyKinds(0));
			Graphics.drawingText(aiming_flag.getName() + "の攻撃！！！", 20, 250);
			Graphics.drawingText("",0,0);
		
			switch(attackerLine){
			case 0: setEnemyColor(aimingEnemy);
					aiming_flag.NORMAL_ATTACK();
					aiming_flag.ATTACKING_LINE_A();
					break;
			case 1: setEnemyColor(aimingEnemy);
					aiming_flag.NORMAL_ATTACK();
					aiming_flag.ATTACKING_LINE_B();
					break;
			case 2: setEnemyColor(aimingEnemy);
					aiming_flag.NORMAL_ATTACK();
					aiming_flag.ATTACKING_LINE_C();
					break;
			}
		
			JudgeAvoid();
			
			if (avoid_flag != true && aimed_flag.getHP() > 0){
				switch(defenderLine){
					case 0: setEnemyColor(aimedEnemy);
							aimed_flag.DAMAGED_LINES_A();
							break;
					case 1: setEnemyColor(aimedEnemy);
							aimed_flag.DAMAGED_LINES_B();
							break;
				}
			}
			dont_aim_dead();
			dont_aimed_dead();
		}
		decreaseHP();
	}
// ダメージステップの処理
	public static void decreaseHP(){
		if (aiming_flag.getMagic_flag() == true){
			if (avoid_flag == true){
				setEnemyColor(getEnemyKinds(0));
				Graphics.drawingText("危機一髪！" + aimed_flag.getName() + "は、うまく回避できた！！", 20, 325);
				Graphics.drawingText("",0,0);
				aimed_flag.setMagic_flag(false);
			}
			else if (avoid_flag == false){
				setEnemyColor(getEnemyKinds(0));
				damageStep1 = aimed_flag.getHP() + aiming_flag.getDamage() - aimed_flag.getDEFENCE();
				aimed_flag.reduceHP(damageStep1);
				aimed_flag.setMagic_flag(false);
			}
		}
		else if (aiming_flag.getMagic_flag() == false){
			if (avoid_flag == true){
				setEnemyColor(getEnemyKinds(0));
				Graphics.drawingText(aimed_flag.getName() + "は、うまく回避した！！", 20, 325);
				Graphics.drawingText("",0,0);
			}
			else if (avoid_flag == false){
				setEnemyColor(getEnemyKinds(0));
				if (aiming_flag.getATTACK() > aimed_flag.getDEFENCE()){
					damageStep1 = ( aiming_flag.getATTACK() - aimed_flag.getDEFENCE());
				}
				else if (aiming_flag.getATTACK() < aimed_flag.getDEFENCE()){
					damageStep1 = damage_least;
				}
				aimed_flag.reduceHP(damageStep1);
			}
		}
		while ((!avoid_flag) && aimed_flag.getHP() < 0){
			 aimed_flag.reduceHP(-1);
		}
		if ((!avoid_flag) && aimed_flag.getHP() == 0){
			aimed_flag.DESTROY_LINE();
			setEnemyColor(getEnemyKinds(0));
			Graphics.drawingText(aimed_flag.getName() + "に" + damageStep1 + "のダメージを与えた！！！", 20, 325);
			Graphics.drawingText("",0,0);
			Graphics.drawingText(aimed_flag.getName() + "は、星屑となった！！", 20, 375);
			Graphics.drawingText("",0,0);
			if (death1 == null){
				death1 = aimed_flag; 				
			}
			else if (death1 != null){
				death2 = aimed_flag;
				if (death2 == null){
					death2 = aimed_flag;
				}
				else if (death2 != null){
					death3 = aimed_flag;
				}
			}
			deadSetterIncrement(1);
			aimed_flag.setDead_flag(true);
			dont_aim_dead();
			dont_aimed_dead();
		}
		else if ((!avoid_flag) && damageStep1 > 0){
			setEnemyColor(getEnemyKinds(0));
			Graphics.drawingText(aimed_flag.getName() + "に" + damageStep1 + "のダメージを与えた！！！", 20, 325);
			Graphics.drawingText("",0,0);
			damageStep1 = 0;
		}
	}
	
// 特殊技能を使った時の処理
	public static void magicAttack(){
		
	if (dead_count >= 3){
		scene = S_ENDING1;
	}
	else if (dead_count < 3){
		dont_aimed_dead();
		dont_aim_dead();
		if (aiming_flag.getHitFlag() != true){
			int attackEffect = rand.nextInt(4);
			switch (attackEffect){
			case 0: setEnemyColor(getEnemyKinds(0));
					Graphics.drawingText(aiming_flag.getName() + "の必殺技攻撃！！！", 20, 250);
					Graphics.drawingText("",0,0);
					setEnemyColor(aimingEnemy);
					aiming_flag.SPECIAL_ACTION1();
					aiming_flag.SPECIAL_ACTION_LINE_1A();
					break;
			case 1: setEnemyColor(getEnemyKinds(0));
					Graphics.drawingText(aiming_flag.getName() + "の必殺技攻撃！！！", 20, 250);
					Graphics.drawingText("",0,0);
					setEnemyColor(aimingEnemy);
					aiming_flag.SPECIAL_ACTION1();
					aiming_flag.SPECIAL_ACTION_LINE_1B();
					break;
			case 2: setEnemyColor(getEnemyKinds(0));
					Graphics.drawingText(aiming_flag.getName() + "の必殺技攻撃！！！", 20, 250);
					Graphics.drawingText("",0,0);
					setEnemyColor(aimingEnemy);
					aiming_flag.SPECIAL_ACTION2();
					aiming_flag.SPECIAL_ACTION_LINE_2A();
					break;
			case 3: setEnemyColor(getEnemyKinds(0));
					Graphics.drawingText(aiming_flag.getName() + "の必殺技攻撃！！！", 20, 250);
					Graphics.drawingText("",0,0);
					setEnemyColor(aimingEnemy);
					aiming_flag.SPECIAL_ACTION2();
					aiming_flag.SPECIAL_ACTION_LINE_2B();
					break;
			}
		} else if(aiming_flag.getHitFlag() == true){
			int lines = rand.nextInt(3);
			aiming_flag.NORMAL_ATTACK();
			switch (lines){
			case 0: setEnemyColor(aimingEnemy);
					aiming_flag.NORMAL_ATTACK();
					aiming_flag.ATTACKING_LINE_A();
					break;
			case 1: setEnemyColor(aimingEnemy);
					aiming_flag.NORMAL_ATTACK();
					aiming_flag.ATTACKING_LINE_B();
					break;
			case 2: setEnemyColor(aimingEnemy);
					aiming_flag.NORMAL_ATTACK();
					aiming_flag.ATTACKING_LINE_C();
					break;
				}
			}
		switch (aimedEnemy){
		case 0: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy1)));
				setAimed_flag(Enemy1);
				break;
		case 1: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy2)));
				setAimed_flag(Enemy2);
				break;
		case 2: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy3)));
				setAimed_flag(Enemy3);
				break;
		case 3: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy4)));
				setAimed_flag(Enemy4);
				break;
		}
		dont_aim_dead();
		dont_aimed_dead();

		switch (aimedEnemy){
		case 0: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy1)));
				setAimed_flag(Enemy1);
				break;
		case 1: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy2)));
				setAimed_flag(Enemy2);
				break;
		case 2: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy3)));
				setAimed_flag(Enemy3);
				break;
		case 3: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy4)));
				setAimed_flag(Enemy4);
				break;
			}
		dont_aim_dead();
		dont_aimed_dead();
		}
		decreaseHP();
	}

	
// 回避するかの判定の処理
	public static void JudgeAvoid(){
		setAimedEnemy(rand.nextInt(4));
		while (aimingEnemy == aimedEnemy){
			setAimedEnemy(rand.nextInt(4));
		}
		switch (aimedEnemy){
		case 0: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy1)));
				setAimed_flag(Enemy1);
				break;
		case 1: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy2)));
				setAimed_flag(Enemy2);
				break;
		case 2: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy3)));
				setAimed_flag(Enemy3);
				break;
		case 3: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy4)));
				setAimed_flag(Enemy4);
				break;
		}
		
		if (aimed_flag.getHP() == 0){
			setAimedEnemy(rand.nextInt(4));
			while (aimingEnemy == aimedEnemy){
				setAimedEnemy(rand.nextInt(4));
			}
			switch (aimedEnemy){
			case 0: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy1)));
					setAimed_flag(Enemy1);
					break;
			case 1: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy2)));
					setAimed_flag(Enemy2);
					break;
			case 2: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy3)));
					setAimed_flag(Enemy3);
					break;
			case 3: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy4)));
					setAimed_flag(Enemy4);
					break;
			}
		}
		
		int judge = aiming_flag.getAccuracy() / aimed_flag.getAvoid();
		if ( rand.nextInt(10) <= judge ){
			avoid_flag = true;
		}
		else if ( rand.nextInt(10) > judge ){
			avoid_flag = false;
		}
	}
	
// スリープ処理
	public void sleep(int time){
		try{
			Thread.sleep(time);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
// startメソッド
	public void run(){
		while(thread!=null){
			// 初期化
			if (scene<0){
				scene = init;
				sleep(Game1.sleep_time);
				scene = S_INITIALIZE;
			}
			else if (scene == S_INITIALIZE){
				sleep(Game1.sleep_time);
				initialize();
				InitEnemyMake();
				if (ONE_TIME_SKIP == false){					
					waitSelect();
				}
				ONE_TIME_SKIP = true;
				scene = S_APPEAR1;
			}
			else if (scene == S_APPEAR1){
				sleep(Game1.sleep_time);
				PlaySound();
				g.lock();
				lock_flag = true;
				clear();
				drawMessageFrame();
				drawEnemys();
				setEnemyColor(1);
				Enemy1.APPEARING_LINES();
				g.unlock();
				lock_flag = false;
				waitSelect();
				scene = S_APPEAR2;
			}
			else if (scene == S_APPEAR2){
				sleep(Game1.sleep_time);
				g.lock();
				lock_flag = true;
				clear();
				drawMessageFrame();
				drawEnemys();
				setEnemyColor(2);
				Enemy2.APPEARING_LINES();
				g.unlock();
				lock_flag = false;
				waitSelect();
				scene = S_APPEAR3;
			}
			else if (scene == S_APPEAR3){
				sleep(Game1.sleep_time);
				g.lock();
				lock_flag = true;
				clear();
				drawMessageFrame();
				drawEnemys();
				setEnemyColor(3);
				Enemy3.APPEARING_LINES();
				g.unlock();
				lock_flag = false;
				waitSelect();
				scene = S_APPEAR4;
			}
			else if (scene == S_APPEAR4){
				sleep(Game1.sleep_time);
				g.lock();
				lock_flag = true;
				clear();
				drawMessageFrame();
				drawEnemys();
				setEnemyColor(4);
				Enemy4.APPEARING_LINES();
				g.unlock();
				lock_flag = false;
				waitSelect();
				scene = S_BETTING;
			}
			else if (scene == S_BETTING){
				sleep(Game1.sleep_time);
				stopSound();
				g.lock();
				lock_flag = true;
				clear();
				drawMessageFrame();
				drawEnemys();
				setEnemyColor(getEnemyKinds(0));
				Graphics.drawingText("どのキャラクターが勝つと思う？", 20, 250);
				Graphics.drawingText("",0,0);
				setEnemyColor(getEnemyKinds(1));
				Graphics.drawingText("一番左・・・１", 20, 275);
				Graphics.drawingText("",0,0);
				setEnemyColor(getEnemyKinds(2));
				Graphics.drawingText("　左から二番目・・・２", 250, 275);
				Graphics.drawingText("",0,0);
				setEnemyColor(getEnemyKinds(3));
				Graphics.drawingText("左から3番目・・・３　", 20, 300);
				Graphics.drawingText("",0,0);
				setEnemyColor(getEnemyKinds(4));
				Graphics.drawingText("一番右・・・４", 250, 300);
				Graphics.drawingText("",0,0);
				g.unlock();
				lock_flag = false;
				waitSelect();
				PlaySound();
				scene = S_BATTLE1;
			}
			else if (scene == S_BATTLE1){
				sleep(Game1.sleep_time);
				g.lock();
				lock_flag = true;
				clear();
				drawMessageFrame();
				drawEnemys();
				if (deadCount() < 3){
					dont_aim_yourself();
					ChooseAttack();
					g.unlock();
					lock_flag = false;
					waitSelect();
					scene = S_BATTLE2;
				}
				else if (deadCount() >= 3){
					
					waitSelect();
					scene = S_ENDING1;					
				}
			}
			else if (scene == S_BATTLE2){
				dont_aim_yourself();
				sleep(Game1.sleep_time);
				g.lock();
				lock_flag = true;
				clear();
				drawMessageFrame();
				drawEnemys();
				if (deadCount() < 3){
					ChooseAttack();
					g.unlock();
					lock_flag = false;
					waitSelect();
					scene = S_BATTLE1;
				}
				else if (deadCount() >= 3){
					waitSelect();
					scene = S_ENDING1;
					g.unlock();
					lock_flag = false;
				}
			}
			
			else if (scene == S_ENDING1){
				stopSound();
				if (lock_flag == false){
					g.lock();					
				}
				lock_flag = true;
				clear();
				sleep(Game1.sleep_time);
				drawMessageFrame();
				drawEnemys();
				if (deadCount() >= 3){
					Graphics.drawingText(aiming_flag.getName() + "の勝利！！！", 20, 250);
					Graphics.drawingText("",0,0);
					sleep(Game1.sleep_time);
					if (getchoose() == aiming_flag){
						g.settingColor(Color.WHITE);
						Graphics.drawingText("あなたの勝利です！！！", 20, 275);
						Graphics.drawingText("",0,0);
						g.unlock();
						lock_flag = false;
						stopSound();
						sleep(Game1.sleep_time);
						scene = S_ENDING2;
					}
					else if (getchoose() != aiming_flag){
						g.unlock();
						lock_flag = false;
						stopSound();
						sleep(Game1.sleep_time);
						scene = S_ENDING2;
					}
				}
			}
			else if (scene == S_ENDING2){
					sleep(Game1.sleep_time);
					g.lock();
					lock_flag = true;
					clear();
					drawMessageFrame();
					drawEnemys();
					g.settingColor(Color.WHITE);
					stop_AutoMode();
					Graphics.drawingText("もう一度対戦しますか？", 20, 250);
					Graphics.drawingText("",0,0);
					Graphics.drawingText("はい・・・画面右側を【2回】タッチ！　いいえ・・・左側をタッチ！",20, 275);
					Graphics.drawingText("",0,0);
					g.unlock();
					lock_flag = false;
					if (end == K_RIGHT){
						end_flag = true;
						endInitialize();
					}
					else if (end == K_LEFT){
						g.lock();
						lock_flag = true;
						clear();
						Graphics.drawingText("終了します・・・",20, 350);
						Graphics.drawingText("",0,0);
						g.unlock();
						lock_flag = false;
						sleep(Game1.sleep_time);
						System.exit(0);
						}
					}
				}
			}
// 画面領域を取得する関数
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
        width = w;
        height = h;
    }
	
// 変数Enemy1にキャラクターをセットする関数
	public void setEnemy1(field_status enemy){
		Enemy1 = enemy;
	}

// 変数Enemy1にキャラクターをセットする関数
	public void setEnemy2(field_status enemy){
		Enemy2 = enemy;
	}

// 変数Enemy1にキャラクターをセットする関数
	public void setEnemy3(field_status enemy){
		Enemy3 = enemy;
	}

// 変数Enemy1にキャラクターをセットする関数
	public void setEnemy4(field_status enemy){
		Enemy4 = enemy;
	}

// 変数aiming_flagにキャラクターをセットする関数
	public static void setAiming_flag(field_status enemy){
		aiming_flag = enemy;
	}

// 変数aimed_flagにキャラクターをセットする関数
	public static void setAimed_flag(field_status enemy){
		aimed_flag = enemy;
	}

// 変数dead_countのセット関数
	public void deadSetter(int dead){
		dead_count = dead;
	}
	
// 変数aimingEnemyにint型の値をセットする関数
	public static void setAimingEnemy(int x){
		aimingEnemy = x;
	}

// 変数aimedEnemyにint型の値をセットする関数
	public static void setAimedEnemy(int x){
		aimedEnemy = x;
	}

// 変数ene1にint型の値をセットする関数
	public void setEne1(int x){
		ene1 = x;
	}

// 変数ene2にint型の値をセットする関数
	public void setEne2(int x){
		ene2 = x;
	}

// 変数ene3にint型の値をセットする関数
	public void setEne3(int x){
		ene3 = x;
	}

// 変数ene4にint型の値をセットする関数
	public void setEne4(int x){
		ene4 = x;
	}
	
// 変数chooseにキャラクターをセットする関数
	public void setChoose(field_status enemy){
		choose = enemy;
	}
	
// 変数dead_countのインクリメント処理
	public static void deadSetterIncrement(int dead){
		dead_count += dead;
	}
	
// 初期化する処理
	public void initialize(){
		deadSetter(0);
		setEnd(0);
	}
	
// エンディング時に初期化する処理
	public void endInitialize(){
		Enemy1.setHP(Enemy1.getMaxHP());
		Enemy1.setMP(Enemy1.getMaxMP());
		Enemy2.setHP(Enemy2.getMaxHP());
		Enemy2.setMP(Enemy2.getMaxMP());
		Enemy3.setHP(Enemy3.getMaxHP());
		Enemy3.setMP(Enemy3.getMaxMP());
		Enemy4.setHP(Enemy4.getMaxHP());
		Enemy4.setMP(Enemy4.getMaxMP());
		Enemy1.setDead_flag(false);
		Enemy2.setDead_flag(false);
		Enemy3.setDead_flag(false);
		Enemy4.setDead_flag(false);
		death1 = null;
		death2 = null;
		death3 = null;
		Game1.AUTO_FLAG = false;
		Game1.sleep_time = Game1.NORMAL_SLEEP;
		ONE_TIME_SKIP = false;
		scene = S_INITIALIZE;
	}
	
// エンディングでゲームを続けるか質問する関数
	public void setEnd(int x){
		this.end = x;
	}
	
// キャラクターが死んだ数を数える処理
	public int deadCount(){
		return dead_count;
	}
	
// サウンドの再生処理
	public void PlaySound(){
		try{
			stopSound();
			player = MediaPlayer.create(getContext(), R.raw.battle);
			player.seekTo(0);
			player.setLooping(true);
			player.start();
			player.setOnCompletionListener(Game1Activity.this);
			} catch (Exception e){
			e.printStackTrace();
		}
	}
	
// サウンドの停止処理
	public void stopSound(){
		try{
			player.stop();
			player.setOnCompletionListener(null);
			player.release();
			player=null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
// サウンドの終了
	public void onCompletion(MediaPlayer mediaPlayer){
		stopSound();
	}
	
    //タッチイベントの処理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX=(int)event.getX();
        int touchY=(int)event.getY();
        int touchAction=event.getAction();
        if (touchAction==MotionEvent.ACTION_UP) {
            if (scene==S_ENDING2) {
            	setEnd((touchX-width/2<0)?K_LEFT:K_RIGHT);
            	key = K_SELECT;
            }
            else if (scene == S_APPEAR1 || scene == S_APPEAR2 ||
            		scene == S_APPEAR3 || scene == S_APPEAR4 ||
            		scene == S_BATTLE1 || scene == S_BATTLE2 ||
            		scene == S_ENDING1 || scene == S_INITIALIZE  ){
            	if (touchX > 0){
            		key = K_SELECT;            		
            	}
            }
            else if (scene==S_BETTING) {
                if (0<touchX && touchX<width/4 &&
                    touchY<height/2) {
                    setChoose(Enemy1);
                    key = K_SELECT;
                } 
                else if (width/4<touchX && touchX<width/4*2 &&
                		touchY<height/2) {
                	setChoose(Enemy2);
                	key = K_SELECT;
            	}
            	else if (width/4*2<touchX && touchX<width/4*3 &&
            		touchY<height/2) {
            		setChoose(Enemy3);
            		key = K_SELECT;
                }
            	else if (width/4*3<touchX && touchX<width &&
        			touchY<height/2){
        			setChoose(Enemy4);
        			key = K_SELECT;
        		}
            }
        }
        return true;
    } 
    
    //決定キー待ち
    private void waitSelect() {
    	if (Game1.AUTO_FLAG == false){
        	key=K_NONE;
        	while (key!=K_SELECT){
        		sleep(Game1.sleep_time);
        	}
    	}
    	else if (Game1.AUTO_FLAG == true){
    		//オートフラグがtrueのときは何も処理をしないでスキップする
    	}
	}

	
// 文字列を削除する処理
	public void clear(){
		g.drawRectfill(5,height/2+5,width-5,height-5);
	}

// キャラクターごとに色分けをする関数
	public static void setEnemyColor(int enemy){
		switch (enemy){
		case 1: Graphics.redLine();
			break;
		case 2: Graphics.greenLine();
			break;
		case 3: Graphics.yellowLine();
			break;
		case 4: Graphics.blueLine();
			break;
			default: Graphics.whiteLine();				
			break;
		}
	}

// キャラクターごとに色分けをする関数
	public static int getEnemyKinds(int place){
		if (place == PLACE1){
			return 1;
		}
		else if (place == PLACE2){
			return 2;
		}
		else if (place == PLACE3){
			return 3;
		}
		else if (place == PLACE4){
			return 4;
		}
		return 0;	
	}

// キャラクターごとの色分けをする関数
	public static int getEnemyPlaces(field_status enemy){
		if (enemy == Enemy1){
			return PLACE1;
		}
		else if (enemy == Enemy2){
			return PLACE2;
		}
		else if (enemy == Enemy3){
			return PLACE3;
		}
		else if (enemy == Enemy4){
			return PLACE4;
		}
		else{
				return 999;
			}
		}
	
// ランダム行動関数
	public static void dont_aim_yourself(){
		aimedEnemy = rand.nextInt(4);
		switch (aimedEnemy){
		case 0: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy1)));
				setAimed_flag(Enemy1);
				break;
		case 1: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy2)));
				setAimed_flag(Enemy2);
				break;
		case 2: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy3)));
				setAimed_flag(Enemy3);
				break;
		case 3: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy4)));
				setAimed_flag(Enemy4);
				break;
		}
		aimingEnemy = rand.nextInt(4);
		switch (aimingEnemy){
		case 0: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy1)));
				setAiming_flag(Enemy1);
				break;
		case 1: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy2)));
				setAiming_flag(Enemy2);
				break;
		case 2: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy3)));
				setAiming_flag(Enemy3);
				break;
		case 3: setEnemyColor(getEnemyKinds(getEnemyPlaces(Enemy4)));
				setAiming_flag(Enemy4);
				break;
		}
	}
	
// 死んでいるキャラクターが攻撃しないようにする関数
	public static void dont_aim_dead(){
		while (aiming_flag == death1 || aiming_flag == death2 || aiming_flag == death3 || aiming_flag == aimed_flag){
			dont_aim_yourself();
			dont_aim_dead();
		}
	}
	
// 死んでいるキャラクターを狙われないようにする関数
	public static void dont_aimed_dead(){
		// 狙われたキャラクターが死んでいるとき
		while (aimed_flag == death1 || aimed_flag == death2 || aimed_flag == death3 || aimed_flag == aiming_flag){
			dont_aim_yourself();
			dont_aimed_dead();
		}			
	}
	
	// Autoボタンの処理をエンディング直前で強制終了させる関数
		public void stop_AutoMode(){
			Game1.AUTO_FLAG = false;
			Game1.sleep_time = NORMAL_SLEEP;
		}
}