package collector.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import collector.effects.PurpleSearingBlowEffect;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.makeInHand;


@NoPools
@NoCompendium
@Deprecated
public class Hurting extends AbstractCollectorCard {
    public final static String ID = makeID(Hurting.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 10, 2, , , 14, 2


    public Hurting() {//Look at the bottom of the next one
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.selfRetain = true;
        tags.add(expansionContentMod.UNPLAYABLE);
        tags.add(expansionContentMod.KINDLING);
        MultiCardPreview.add(this, new GreaterHurting(), new GreatestHurting());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        /*
        atb(new VFXAction(new PurpleSearingBlowEffect(m.hb.cX, m.hb.cY, 3)));
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        */
    }

    public void onRetained() {
        if (this.upgraded){
        atb(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        }
    }

    @Override
    public void triggerOnExhaust() {
        AbstractCard toAdd = new GreaterHurting();
        if (upgraded) {
            toAdd.upgrade();
        }
        makeInHand(toAdd);
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    public void upp() {
        uDesc();
//        cardsToPreview.upgrade();
        MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
    }
}