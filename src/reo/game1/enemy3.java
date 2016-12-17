package reo.game1;

import android.content.Context;
import android.view.SurfaceHolder;

// キャラクターその１
public class enemy3 extends Graphics implements field_status{

	private final int maxHP = 5555;
	private final int maxMP = 44;
	private static int HP = 5555;
	private static int MP = 44;
	private static final int ATTACK = 55;
	private static final int DEFENCE = 5;
	private boolean hit_flag;
	private final String Name = "ヨルン";
	private static int DAMAGE;
	private static boolean magic_flag;
	private final int accuracy = 5;
	private final int avoid = 5;
	private static boolean dead_flag;
	
	public enemy3(SurfaceHolder holder, Context context) {
		super(holder,context);
	}	
//------ 攻撃行動系メソッド -------//
	
// 通常攻撃
	public void NORMAL_ATTACK(){
		drawingText(getName() + "は剣を振り回した！！", 20, 275);
		drawingText("",0,0);
	}

// 特殊技能１
	public void SPECIAL_ACTION1(){
		drawingText(getName() + "はドラゴンを召還した！！！", 20, 275);
		drawingText("",0,0);
		reduceMP(38);
		DAMAGE = setDamage(2020);
		if (getMP() <= 0){
			reduceHP(30);
			while (getHP() <= 0){
				reduceHP(-1);
			}
			setHitFlag(true);
			DAMAGE = setDamage(2020);
		}
	}

// 特殊技能２
	public void SPECIAL_ACTION2(){
		drawingText(getName() + "は高く飛び上がり、大地に突き刺した！！！", 20 ,275);
		drawingText("",0,0);
		reduceMP(8);
		DAMAGE = setDamage(getATTACK()*5);
		if (getMP() <= 0){
			reduceHP(27);
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
		drawingText("「ヨルン」だ。おまえたちは仲間になれ！" , 20, 250);
		drawingText("",0,0);
	}
	
// キャラクター攻撃時のセリフ１	
	public void ATTACKING_LINE_A(){
		drawingText("ヨルン：ジャッカルブレード！！" , 20, 300);		
		drawingText("",0,0);
	}

// キャラクター攻撃時のセリフ2
	public void ATTACKING_LINE_B(){
		drawingText("ヨルン：ドラゴンソード・モード・『リーパー』！！" , 20, 300);	
		drawingText("",0,0);
	}
	
// キャラクター攻撃時のセリフ3
	public void ATTACKING_LINE_C(){
		drawingText("ヨルン：もらったぁぁぁぁ！！！" , 20, 300);	
		drawingText("",0,0);
	}
	
// キャラクター特殊技能１のセリフA
	public void SPECIAL_ACTION_LINE_1A(){
		drawingText("ヨルン：ドラゴンソード・モード・『ドラゴン』!!!!" , 20, 300);
		drawingText("",0,0);
	}

// キャラクター特殊技能１のセリフB
	public void SPECIAL_ACTION_LINE_1B(){
		drawingText("ヨルン：いっけぇ、ドラゴン。ファイアーブレスだ！！" , 20, 300);
		drawingText("",0,0);
	}
	
// キャラクター特殊技能２のセリフA
	public void SPECIAL_ACTION_LINE_2A(){
		drawingText("ヨルンは龍脈を爆発させた！" , 20, 300);
		drawingText("",0,0);
	}
	
// キャラクター特殊技能２のセリフB
	public void SPECIAL_ACTION_LINE_2B(){
		drawingText("ヨルン：ちっ、外したか。" , 20, 300);
		drawingText("",0,0);
	}
	
// キャラクターがダメージを受けた時のセリフA
	public void DAMAGED_LINES_A(){
		drawingText("ヨルン：なかなかやるじゃねぇか！" , 20, 350);		
		drawingText("",0,0);
	}

// キャラクターがダメージを受けた時のセリフB
	public void DAMAGED_LINES_B(){
		drawingText("ヨルン：痛っ！" , 20, 350);
		drawingText("",0,0);
	}
	
// キャラクター死亡時のセリフ
	public void DESTROY_LINE(){
		drawingText("ヨルン：やだよおおおおおおお！！！！！" , 20, 350);
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
		setHP(MP);
	}

	public int setDamage(int damage) {
		DAMAGE = damage;
		return DAMAGE;
	}

}