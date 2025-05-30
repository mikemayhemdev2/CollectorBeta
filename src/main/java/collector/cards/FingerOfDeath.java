package collector.cards;

import collector.effects.FingerOfDeathEffect;
import collector.powers.DoomPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class FingerOfDeath extends AbstractCollectorCard {
    public final static String ID = makeID(FingerOfDeath.class.getSimpleName());
    // intellij stuff attack, enemy, rare, 50, , , , ,

    public FingerOfDeath() {
        super(ID, 4, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 50;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(p, new FingerOfDeathEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1f));
        forAllMonstersLiving(q -> applyToEnemy(q, new DoomPower(q, magicNumber)));
    }

    public void upp() {
        upgradeBaseCost(3);
    }
}