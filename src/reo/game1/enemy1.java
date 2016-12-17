package reo.game1;

import android.content.Context;
import android.view.SurfaceHolder;

// キャラクターその１
public class enemy1 extends Graphics implements field_status {

	private final int maxHP = 255;
	private final int maxMP = 19;
	private static int HP = 255;
	private static int MP = 19;
	private static final int ATTACK = 255;
	private static final int DEFENCE = 137;
	private boolean hit_flag;
	private final String Name = "ツバキ";
	private static int DAMAGE;
	private static boolean magic_flag;
	private final int accuracy = 6;
	private final int avoid = 2;
	private static boolean dead_flag;
	
	public enemy1(SurfaceHolder holder, Context context) {
		super(holder,context);
	}

	// ------ 攻撃行動系メソッド -------//

	// 通常攻撃
	public void NORMAL_ATTACK() {
		drawingText(getName() + "は、真剣で切り捨てた！", 20, 275);
		drawingText("",0,0);
	}

	// 特殊技能１
	public void SPECIAL_ACTION1() {
		drawingText(getName() + "は一閃を放った！", 20,  275);
		drawingText("",0,0);
		reduceMP(15);
		DAMAGE = setDamage(9999);
		if (getMP() <= 0) {
			reduceHP(30);
			while (getHP() <= 0) {
				reduceHP(-1);
			}
			setHitFlag(true);
			DAMAGE = setDamage(9999);
		}
	}

	// 特殊技能２
	public void SPECIAL_ACTION2() {
		drawingText(getName() + "は血祭りをあげた！", 20,  275);
		drawingText("",0,0);
		reduceMP(8);
		DAMAGE = setDamage(getATTACK() * 2);
		if (getMP() <= 0) {
			reduceHP(16);
			while (getHP() <= 0) {
				reduceHP(-1);
			}
			setHitFlag(true);
			DAMAGE = setDamage(getATTACK() * 2);
		}
	}

	// 特殊ステータス
	public void SPECIAL_STATUS() {

	}

// -------メッセージ系メソッド---------//
	
	// キャラクター登場時のセリフ
	public void APPEARING_LINES(){
		drawingText("私は「ツバキ」！戦場の乙女なり！！" , 20, 250);
		drawingText("",0,0);
	}

	// キャラクター攻撃時のセリフ１
	public void ATTACKING_LINE_A() {
		drawingText("ツバキ：みねうち！！", 20,  300);
		drawingText("",0,0);
	}

	// キャラクター攻撃時のセリフ2
	public void ATTACKING_LINE_B() {
		drawingText("ツバキ：ほらほらどうした！もう終わりか？", 20,  300);
		drawingText("",0,0);
	}

	// キャラクター攻撃時のセリフ3
	public void ATTACKING_LINE_C() {
		drawingText("ツバキ：滅せよ！！！", 20,  300);
		drawingText("",0,0);
	}

	// キャラクター特殊技能１のセリフA
	public void SPECIAL_ACTION_LINE_1A() {
		drawingText("ツバキ：・・・見えた！『一刀両断』", 20,  300);
		drawingText("",0,0);
	}

	// キャラクター特殊技能１のセリフB
	public void SPECIAL_ACTION_LINE_1B() {
		drawingText("ツバキ：・・・わが刃の前にひれ伏すがいい！！", 20,  300);
		drawingText("",0,0);
	}

	// キャラクター特殊技能２のセリフA
	public void SPECIAL_ACTION_LINE_2A() {
		drawingText("ツバキ：激痛にのたうち消えよ！", 20,  300);
		drawingText("",0,0);
	}

	// キャラクター特殊技能２のセリフB
	public void SPECIAL_ACTION_LINE_2B() {
		drawingText("ツバキ：貴様にかける情けなどない・・・！", 20,  300);
		drawingText("",0,0);
	}

	// キャラクターがダメージを受けた時のセリフA
	public void DAMAGED_LINES_A() {
		drawingText("ツバキ：・・・そんなもの！効かない！", 20,  350);
		drawingText("",0,0);
	}

	// キャラクターがダメージを受けた時のセリフB
	public void DAMAGED_LINES_B() {
		drawingText("ツバキ：ふん、痛くもかゆくもないわ！", 20,  350);
		drawingText("",0,0);
	}

	// キャラクター死亡時のセリフ
	public void DESTROY_LINE() {
		drawingText("ツバキ：無念・・・ぐはっ・・・・・・", 20,  350);
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