package collector.cards;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.cardmods.PropertiesMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;

public class Hoard extends AbstractCollectorCard {
    public final static String ID = makeID(Hoard.class.getSimpleName());

    public Hoard() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        //isPyre();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));

        if (p.drawPile.size() >= 20){
            atb(new DrawCardAction(2));
        }

    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}