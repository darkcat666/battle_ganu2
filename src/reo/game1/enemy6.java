package reo.game1;

import android.content.Context;
import android.view.SurfaceHolder;

// キャラクターその１
public class enemy6 extends Graphics implements field_status{

	private final int maxHP = 701;
	private final int maxMP = 29;
	private static int HP = 701;
	private static int MP = 29;
	private static final int ATTACK = 552;
	private static final int DEFENCE = 731;
	private boolean hit_flag;
	private final String Name = "レイラ";
	private static int DAMAGE;
	private static boolean magic_flag;
	private final int accuracy = 9;
	private final int avoid = 9;
	private static boolean dead_flag;
	
	public enemy6(SurfaceHolder holder, Context context) {
		super(holder,context);
	}	
//------ 攻撃行動系メソッド -------//
	
// 通常攻撃
	public void NORMAL_ATTACK(){
		drawingText(getName() + "は、真剣に手を添えた。", 20, 275);
		drawingText("",0,0);
	}

// 特殊技能１
	public void SPECIAL_ACTION1(){
		drawingText(getName() + "は五輪の剣を放った！！！", 20, 275);
		drawingText("",0,0);
		reduceMP(33);
		DAMAGE = setDamage(9999);
		if (getMP() <= 0){
			
			reduceHP(51);
			while (getHP() <= 0){
				reduceHP(-1);
			}
			setHitFlag(true);
			DAMAGE = setDamage(9999);
		}
	}

// 特殊技能２
	public void SPECIAL_ACTION2(){
		drawingText(getName() + "は援軍を呼んだ！！！", 20 ,275);
		drawingText("",0,0);
		reduceMP(8);
		DAMAGE = setDamage(getATTACK()*4);
		if (getMP() <= 0){
			reduceHP(50);
			while(getHP() <= 0){
				reduceHP(-1);
			}
			setHitFlag(true);
			DAMAGE = setDamage(getATTACK()*4);
		}
	}

// 特殊ステータス
	public void SPECIAL_STATUS(){
	
	}


//-------メッセージ系メソッド---------//	

//キャラクター登場時のセリフ
	public void APPEARING_LINES(){
		drawingText("「レイラ」・・・それだけさ。" , 20, 250);
		drawingText("",0,0);
	}
	
// キャラクター攻撃時のセリフ１	
	public void ATTACKING_LINE_A(){
		drawingText("レイラは相手の心臓を突き刺した。" , 20, 300);		
		drawingText("",0,0);
	}

// キャラクター攻撃時のセリフ2
	public void ATTACKING_LINE_B(){
		drawingText("レイラ：露払い・・・" , 20, 300);	
		drawingText("",0,0);
	}
	
// キャラクター攻撃時のセリフ3
	public void ATTACKING_LINE_C(){
		drawingText("レイラは相手を睨みつけた。" , 20, 300);	
		drawingText("",0,0);
	}
	
// キャラクター特殊技能１のセリフA
	public void SPECIAL_ACTION_LINE_1A(){
		drawingText("レイラ：五輪の型に刻む！！" , 20, 300);
		drawingText("",0,0);
	}

// キャラクター特殊技能１のセリフB
	public void SPECIAL_ACTION_LINE_1B(){
		drawingText("レイラ：ふ、またつまらないものを切り刻んでしまった・・・" , 20, 300);
		drawingText("",0,0);
	}
	
// キャラクター特殊技能２のセリフA
	public void SPECIAL_ACTION_LINE_2A(){
		drawingText("レイラ：大砲よーい！！うてーーーーーー！！！" , 20, 300);
		drawingText("",0,0);
	}
	
// キャラクター特殊技能２のセリフB
	public void SPECIAL_ACTION_LINE_2B(){
		drawingText("レイラ：鉄砲部隊よーい！！構え！" , 20, 300);
		drawingText("",0,0);
	}
	
// キャラクターがダメージを受けた時のセリフA
	public void DAMAGED_LINES_A(){
		drawingText("レイラ：ふん。" , 20, 350);		
		drawingText("",0,0);
	}

// キャラクターがダメージを受けた時のセリフB
	public void DAMAGED_LINES_B(){
		drawingText("レイラ：・・・よくも！" , 20, 350);
		drawingText("",0,0);
	}
	
// キャラクター死亡時のセリフ
	public void DESTROY_LINE(){
		drawingText("レイラ：武士として死ねる。本望だ。" , 20, 350);
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