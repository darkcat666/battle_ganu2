package reo.game1;

// キャラクターのインターフェースメソッド
public interface field_status{


// キャラクターの行動系メソッド	
	public abstract void NORMAL_ATTACK();// キャラクターの通常攻撃
	
	public abstract void SPECIAL_ACTION1();// キャラクターの特殊技能１
	
	public abstract void SPECIAL_ACTION2();// キャラクターの特殊技能２
	
	public abstract void SPECIAL_STATUS();// キャラクター固有の特殊ステータス

	
// メッセージ系メソッド	
	public abstract void APPEARING_LINES();// キャラクター登場時のセリフ
	
	public abstract void ATTACKING_LINE_A();// キャラクター攻撃時のセリフ１

	public abstract void ATTACKING_LINE_B();// キャラクター攻撃時のセリフ2
	
	public abstract void ATTACKING_LINE_C();// キャラクター攻撃時のセリフ3
	
	public abstract void SPECIAL_ACTION_LINE_1A();// キャラクター特殊技能１のセリフA

	public abstract void SPECIAL_ACTION_LINE_1B();// キャラクター特殊技能１のセリフB
	
	public abstract void SPECIAL_ACTION_LINE_2A();// キャラクター特殊技能２のセリフA
	
	public abstract void SPECIAL_ACTION_LINE_2B();// キャラクター特殊技能２のセリフB
	
	public abstract void DAMAGED_LINES_A();// キャラクターがダメージを受けた時のセリフA

	public abstract void DAMAGED_LINES_B();// キャラクターがダメージを受けた時のセリフB
	
	public abstract void DESTROY_LINE();// キャラクター死亡時のセリフ


//------ゲット系メソッド------//
	public abstract int getMaxHP();
	
	public abstract int getHP();
	
	public abstract int getMaxMP();

	public abstract int getMP();

	public abstract int getATTACK();
	
	public abstract int getDEFENCE();
	
	public abstract boolean getHitFlag();
	
	public abstract String getName();

	public abstract int getDamage();
	
	public abstract boolean getMagic_flag();
	
	public abstract int getAvoid();
	
	public abstract int getAccuracy();
	
	public abstract boolean getDead_flag();
	
//------セット系メソッド------//
	
	public abstract boolean setHitFlag(boolean flag);
	
	public abstract void reduceHP(int hp);
	
	public abstract void setHP(int hp);
	
	public abstract void setMP(int mp);
	
	public abstract void reduceMP(int mp);
	
	public abstract int setDamage(int damage);
	
	public abstract boolean setMagic_flag(boolean flag);
	
	public abstract void setDead_flag(boolean flag);
	
}