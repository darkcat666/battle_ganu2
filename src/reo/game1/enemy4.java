package reo.game1;

import android.content.Context;
import android.view.SurfaceHolder;

// キャラクターその１
public class enemy4 extends Graphics implements field_status{

	private final int maxHP = 7777;
	private final int maxMP = 666;
	private static int HP = 7777;
	private static int MP = 666;
	private static final int ATTACK = 12;
	private static final int DEFENCE = 13;
	private boolean hit_flag;
	private final String Name = "カサネ";
	private static int DAMAGE;
	private static boolean magic_flag;
	private final int accuracy = 8;
	private final int avoid = 8;
	private static boolean dead_flag;

	public enemy4(SurfaceHolder holder, Context context) {
		super(holder,context);
	}	
//------ 攻撃行動系メソッド -------//
	
// 通常攻撃
	public void NORMAL_ATTACK(){
		drawingText(getName() + "は、召魔の鈴を鳴らした！", 20, 275);
		drawingText("",0,0);
	}

// 特殊技能１
	public void SPECIAL_ACTION1(){
		drawingText(getName() + "は大地の精気を闇の女王に捧げた！", 20, 275);
		drawingText("",0,0);
		reduceMP(200);
		DAMAGE = setDamage(999);
		if (getMP() <= 0){
			reduceHP(2222);
			while (getHP() <= 0){
				reduceHP(-1);
			}
			setHitFlag(true);
			DAMAGE = setDamage(999);
		}
	}

// 特殊技能２
	public void SPECIAL_ACTION2(){
		drawingText(getName() + "は不吉な呪文を囁いた。", 20 ,275);
		drawingText("",0,0);
		reduceMP(77);
		DAMAGE = setDamage(getATTACK()*66);
		if (getMP() <= 0){
			reduceHP(777);
			while(getHP() <= 0){
				reduceHP(-1);
			}
			setHitFlag(true);
			DAMAGE = setDamage(getATTACK()*66);
		}
	}

// 特殊ステータス
	public void SPECIAL_STATUS(){
	
	}


//-------メッセージ系メソッド---------//	

//キャラクター登場時のセリフ
	public void APPEARING_LINES(){
		drawingText("「カサネ」。人間はいや。" , 20, 250);
		drawingText("",0,0);
	}
	
// キャラクター攻撃時のセリフ１	
	public void ATTACKING_LINE_A(){
		drawingText("カサネ：闇が世界を覆うわ。" , 20, 300);		
		drawingText("",0,0);
	}

// キャラクター攻撃時のセリフ2
	public void ATTACKING_LINE_B(){
		drawingText("カサネ：光が世界をつぶしていく。" , 20, 300);	
		drawingText("",0,0);
	}
	
// キャラクター攻撃時のセリフ3
	public void ATTACKING_LINE_C(){
		drawingText("カサネ：・・・来なさい。準備は整うわ。" , 20, 300);	
		drawingText("",0,0);
	}
	
// キャラクター特殊技能１のセリフA
	public void SPECIAL_ACTION_LINE_1A(){
		drawingText("カサネ：いけにえになりなぁぁ！！" , 20, 300);
		drawingText("",0,0);
	}

// キャラクター特殊技能１のセリフB
	public void SPECIAL_ACTION_LINE_1B(){
		drawingText("カサネ：あーははは、もうおしまいよ！！" , 20, 300);
		drawingText("",0,0);
	}
	
// キャラクター特殊技能２のセリフA
	public void SPECIAL_ACTION_LINE_2A(){
		drawingText("カサネ：死んじゃいなぁ！" , 20, 300);
		drawingText("",0,0);
	}
	
// キャラクター特殊技能２のセリフB
	public void SPECIAL_ACTION_LINE_2B(){
		drawingText("カサネ：消えちゃいなぁ！" , 20, 300);
		drawingText("",0,0);
	}
	
// キャラクターがダメージを受けた時のセリフA
	public void DAMAGED_LINES_A(){
		drawingText("カサネ：痛いわ。" , 20, 350);		
		drawingText("",0,0);
	}

// キャラクターがダメージを受けた時のセリフB
	public void DAMAGED_LINES_B(){
		drawingText("カサネ：痛い。" , 20, 350);
		drawingText("",0,0);
	}
	
// キャラクター死亡時のセリフ
	public void DESTROY_LINE(){
		drawingText("カサネ：・・・『次』こそは、世界を闇に埋めてやる・・・" , 20, 350);
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