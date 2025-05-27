package collector.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Blind;
import com.megacrit.cardcrawl.cards.colorless.Trip;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class Invigorate extends AbstractCollectorCard {
    public final static String ID = makeID(Invigorate.class.getSimpleName());
    // intellij stuff skill, enemy, common, , , , , , 

    public Invigorate() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 2;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new VulnerablePower(m, this.magicNumber, false));
        applyToSelf(new StrengthPower(AbstractDungeon.player, magicNumber));
        applyToSelf(new LoseStrengthPower(AbstractDungeon.player, magicNumber));


    }

    public void upp() {

        upgradeMagicNumber(1);
    }
}
