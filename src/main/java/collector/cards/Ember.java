package collector.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import expansioncontent.expansionContentMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToSelf;

public class Ember extends AbstractCollectorCard {
    public final static String ID = makeID(Ember.class.getSimpleName());
    // intellij stuff skill, none, special, , , , , 1, 1

    public Ember() {
        super(ID, -2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 3;
        selfRetain = true;
        exhaust = true;
        tags.add(expansionContentMod.UNPLAYABLE);
        tags.add(expansionContentMod.KINDLING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerOnExhaust() {
        this.addToBot(new VFXAction(AbstractDungeon.player, new InflameEffect(AbstractDungeon.player), 0.1F));
        applyToSelf(new StrengthPower(AbstractDungeon.player, magicNumber));
        applyToSelf(new LoseStrengthPower(AbstractDungeon.player, magicNumber));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}