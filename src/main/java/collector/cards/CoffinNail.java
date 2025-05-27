package collector.cards;

import collector.actions.ModifyCostAction;
import collector.powers.AddCopyNextTurnPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToSelf;
import static utilityClasses.Wiz.atb;

public class CoffinNail extends AbstractCollectorCard {
    public final static String ID = makeID(CoffinNail.class.getSimpleName());
    // intellij stuff attack, enemy, rare, 5, 1, , , 5, 1

    public CoffinNail() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 24;
        //baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL); // Could have better VFX
    }

    @Override
    public void triggerOnExhaust() {
        atb(new ModifyCostAction(this.uuid, -1));
        applyToSelf(new AddCopyNextTurnPower(this));
    }

    public void upp() {
        upgradeDamage(6);
        //upgradeMagicNumber(1);
    }
}