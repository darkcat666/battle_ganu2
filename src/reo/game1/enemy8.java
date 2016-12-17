package reo.game1;

import android.content.Context;
import android.view.SurfaceHolder;

// キャラクターその１
public class enemy8 extends Graphics implements field_status{

	private final int maxHP = 8888;
	private final int maxMP = 9999;
	private static int HP = 8888;
	private static int MP = 9999;
	private static final int ATTACK = 100;
	private static final int DEFENCE = 3;
	private boolean hit_flag;
	private final String Name = "チャチャ";
	private static int DAMAGE;
	private static boolean magic_flag;
	private final int accuracy = 2;
	private final int avoid = 2;
	private static boolean dead_flag;

	public enemy8(SurfaceHolder holder, Context context) {
		super(holder,context);
	}	
	
// 通常攻撃
	public void NORMAL_ATTACK(){
		drawingText(getName() + "は、部下に借りてきた剣で切りつけた！", 20, 275);
		drawingText("",0,0);
	}

// 特殊技能１
	public void SPECIAL_ACTION1(){
		drawingText(getName() + "は魔法の弓矢を引き絞った！", 20, 275);
		drawingText("",0,0);
		reduceMP(3333);
		DAMAGE = setDamage(7777);
		if (getMP() <= 0){
			reduceHP(4444);
			while (getHP() <= 0){
				reduceHP(-1);
			}
			setHitFlag(true);
			DAMAGE = setDamage(7777);
		}
	}

// 特殊技能２
	public void SPECIAL_ACTION2(){
		drawingText(getName() + "は相手の首をつかんだ。", 20 ,275);
		drawingText("",0,0);
		reduceMP(206);
		DAMAGE = setDamage(getATTACK()*5);
		if (getMP() <= 0){
			reduceHP(770);
			while(getHP() <= 0){
				reduceHP(-1);
			}
			setHitFlag(true);
			DAMAGE = setDamage(getATTACK()*5);
		}
	}

// 特殊ステータス
	public void SPECIAL_STATUS(){
	
	}


//-------メッセージ系メソッド---------//	

//キャラクター登場時のセリフ
	public void APPEARING_LINES(){
		drawingText("わたし「チャチャ」。王女様よ。仲良くしてね！" , 20, 250);
		drawingText("",0,0);
	}
	
// キャラクター攻撃時のセリフ１	
	public void ATTACKING_LINE_A(){
		drawingText("チャチャ：えいっ！" , 20, 300);		
		drawingText("",0,0);
	}

// キャラクター攻撃時のセリフ2
	public void ATTACKING_LINE_B(){
		drawingText("チャチャ：ていやぁ！！" , 20, 300);	
		drawingText("",0,0);
	}
	
// キャラクター攻撃時のセリフ3
	public void ATTACKING_LINE_C(){
		drawingText("チャチャ：・・・せいやぁっ！！！" , 20, 300);	
		drawingText("",0,0);
	}
	
// キャラクター特殊技能１のセリフA
	public void SPECIAL_ACTION_LINE_1A(){
		drawingText("チャチャ：『ビューティ・セレイン・アロー』！！" , 20, 300);
		drawingText("",0,0);
	}

// キャラクター特殊技能１のセリフB
	public void SPECIAL_ACTION_LINE_1B(){
		drawingText("チャチャ：『ゴッド・バスター・アロー』！！" , 20, 300);
		drawingText("",0,0);
	}
	
// キャラクター特殊技能２のセリフA
	public void SPECIAL_ACTION_LINE_2A(){
		drawingText("チャチャ：・・・よくも私を怒らせたわね。" , 20, 300);
		drawingText("",0,0);
	}
	
// キャラクター特殊技能２のセリフB
	public void SPECIAL_ACTION_LINE_2B(){
		drawingText("チャチャ：・・・私、姫だから力は強いの。" , 20, 300);
		drawingText("",0,0);
	}
	
// キャラクターがダメージを受けた時のセリフA
	public void DAMAGED_LINES_A(){
		drawingText("チャチャ：・・・よくも私を！" , 20, 350);		
		drawingText("",0,0);
	}

// キャラクターがダメージを受けた時のセリフB
	public void DAMAGED_LINES_B(){
		drawingText("チャチャ：よくも、わたしの、大事な、服をーー！！！" , 20, 350);
		drawingText("",0,0);
	}
	
// キャラクター死亡時のセリフ
	public void DESTROY_LINE(){
		drawingText("チャチャは倒れた。" , 20, 350);
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

	public void setHP(int hp){
		HP = hp;
	}
	
	public void setMP(int mp){
		MP = mp;
	}

	public boolean setHitFlag(boolean flag) {
		hit_flag = flag;
		return hit_flag;
	}

	public void reduceHP(int hp) {
		HP = getHP() - hp;
		setHP(HP);
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