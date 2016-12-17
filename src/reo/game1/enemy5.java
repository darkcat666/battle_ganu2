package reo.game1;

import android.content.Context;
import android.view.SurfaceHolder;

// キャラクターその１
public class enemy5 extends Graphics implements field_status{

	private final int maxHP = 2550;
	private final int maxMP = 77;
	private static int HP = 2550;
	private static int MP = 77;
	private static final int ATTACK = 302;
	private static final int DEFENCE = 154;
	private boolean hit_flag;
	private final String Name = "ヘレンローゼ";
	private static int DAMAGE;
	private static boolean magic_flag;
	private final int accuracy = 9;
	private final int avoid = 1;
	private static boolean dead_flag;

	public enemy5(SurfaceHolder holder, Context context) {
		super(holder,context);
	}	
//------ 攻撃行動系メソッド -------//
	
// 通常攻撃
	public void NORMAL_ATTACK(){
		drawingText(getName() + "は武器を地面において、回し蹴りを放った！！", 20, 275);
		drawingText("",0,0);
	}

// 特殊技能１
	public void SPECIAL_ACTION1(){
		drawingText(getName() + "は鉄の槌で気絶するまで殴った！！", 20, 275);
		drawingText("",0,0);
		reduceMP(6);
		DAMAGE = setDamage(676);
		if (getMP() <= 0){
			reduceHP(101);
			while (getHP() <= 0){
				reduceHP(-1);
			}
			setHitFlag(true);
			DAMAGE = setDamage(676);
		}
	}

// 特殊技能２
	public void SPECIAL_ACTION2(){
		drawingText(getName() + "は叫び声をあげた！！！", 20 ,275);
		drawingText("",0,0);
		reduceMP(22);
		DAMAGE = setDamage(getATTACK()*3);
		if (getMP() <= 0){
			reduceHP(1000);
			while(getHP() <= 0){
				reduceHP(-1);
			}
			setHitFlag(true);
			DAMAGE = setDamage(getATTACK()*3);
		}
	}

// 特殊ステータス
	public void SPECIAL_STATUS(){
	
	}


//-------メッセージ系メソッド---------//	

//キャラクター登場時のセリフ
	public void APPEARING_LINES(){
		drawingText("うおおおお！「ヘレンローゼ」、うおおおお！！" , 20, 250);
		drawingText("",0,0);
	}
	
// キャラクター攻撃時のセリフ１	
	public void ATTACKING_LINE_A(){
		drawingText("ヘレンローゼ：くらええええええ！" , 20, 300);		
		drawingText("",0,0);
	}

// キャラクター攻撃時のセリフ2
	public void ATTACKING_LINE_B(){
		drawingText("ヘレンローゼ：ずっどーーーーーん！" , 20, 300);	
		drawingText("",0,0);
	}
	
// キャラクター攻撃時のセリフ3
	public void ATTACKING_LINE_C(){
		drawingText("ヘレンローゼ：おらーーーーーー！！！" , 20, 300);	
		drawingText("",0,0);
	}
	
// キャラクター特殊技能１のセリフA
	public void SPECIAL_ACTION_LINE_1A(){
		drawingText("ヘレンローゼ：ナイイン、スマッシュ！うおーほー！！" , 20, 300);
		drawingText("",0,0);
	}

// キャラクター特殊技能１のセリフB
	public void SPECIAL_ACTION_LINE_1B(){
		drawingText("ヘレンローゼ：泥、肉、飯、豚ーーーーーー！！！" , 20, 300);
		drawingText("",0,0);
	}
	
// キャラクター特殊技能２のセリフA
	public void SPECIAL_ACTION_LINE_2A(){
		drawingText("ヘレンローゼ：にくううううううううう！" , 20, 300);
		drawingText("",0,0);
	}
	
// キャラクター特殊技能２のセリフB
	public void SPECIAL_ACTION_LINE_2B(){
		drawingText("ヘレンローゼ：ぶたあああああああ！" , 20, 300);
		drawingText("",0,0);
	}
	
// キャラクターがダメージを受けた時のセリフA
	public void DAMAGED_LINES_A(){
		drawingText("ヘレンローゼ：ぐぼえ？" , 20, 350);		
		drawingText("",0,0);
	}

// キャラクターがダメージを受けた時のセリフB
	public void DAMAGED_LINES_B(){
		drawingText("ヘレンローゼ：うおお？？" , 20, 350);
		drawingText("",0,0);
	}
	
// キャラクター死亡時のセリフ
	public void DESTROY_LINE(){
		drawingText("ヘレンローゼ：しぬう？う、しぬううううううう・・・！！" , 20, 350);
		drawingText("",0,0);
	}

	// ------ゲット系メソッド------//
	
	public int getMaxHP(){
		return maxHP;
	}

	public int getMaxMP(){
		return maxMP;
	}

	public int getHP() {
		return HP;
	}

	public int getMP() {
		return MP;
	}

	public int getATTACK() {
		return ATTACK;
	}

	public int getDEFENCE() {
		return DEFENCE;
	}

	public boolean getHitFlag() {
		return hit_flag;
	}

	public String getName() {
		return Name;
	}

	public boolean setMagic_flag(boolean flag) {
		magic_flag = flag;
		return magic_flag;
	}

	public int getDamage() {
		return DAMAGE;
	}

	public boolean getMagic_flag() {
		return magic_flag;
	}

	public int getAvoid() {
		return avoid;
	}

	public int getAccuracy() {
		return accuracy;
	}
	
	public boolean getDead_flag(){
		return dead_flag;
	}

	// -------セット系メソッド------//
	
	public void setDead_flag(boolean flag){
		dead_flag = flag;
	}

	public boolean setHitFlag(boolean flag) {
		hit_flag = flag;
		return hit_flag;
	}

	public void reduceHP(int hp) {
		HP = getHP() - hp;
		setHP(HP);
	}
	
	public void setHP(int hp){
		HP = hp;
	}
	
	public void setMP(int mp){
		MP = mp;
	}

	public void reduceMP(int mp) {
		MP = getMP() - mp;
		setMP(MP);
	}

	public int setDamage(int damage) {
		DAMAGE = damage;
		return DAMAGE;
	}

}