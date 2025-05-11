package collector.cards.collectibles;

import collector.CollectorCollection;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.att;

public class MysticCard extends AbstractCollectibleCard {
    public final static String ID = makeID(MysticCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 6, 2

    public MysticCard() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        cardsToPreview = new EnragedCenturion();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void searchPile(CardGroup cardsToSearch) {

        for (AbstractCard c : cardsToSearch.group) {
            if (c instanceof CenturionCard) {
                AbstractCard newthing = new EnragedCenturion();
                if (c.upgraded) newthing.upgrade();
                cardsToSearch.group.add(cardsToSearch.group.indexOf(c), newthing);
                cardsToSearch.group.remove(c);
            }
        }
    }

    @Override
    public void triggerOnExhaust() {
        atb(new HealAction(AbstractDungeon.player, AbstractDungeon.player, magicNumber));

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                searchPile(AbstractDungeon.player.drawPile);
                searchPile(AbstractDungeon.player.discardPile);
                searchPile(AbstractDungeon.player.hand);
                searchPile(AbstractDungeon.player.exhaustPile);
                searchPile(CollectorCollection.combatCollection);
            }
        });


    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}