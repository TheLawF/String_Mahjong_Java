""" 番种类型总表，从上至下依次为：
一般高✔ 老少副✔ 平和✔ 喜相逢✔ 四归一✔ 连六✔ 三色三同顺✔ 一色三节高 一色三步高\n
一色四步高 一色四节高 碰碰和✔ 全双刻✔ 幺九刻✔ 双暗刻✔ 三暗刻✔ 双同刻✔ 三同刻✔\n
暗杠 明杠 双暗杠 三杠子 清一色✔ 混一色✔ 绿一色✔ 五门齐✔ 七对子✔ 大七星 连七对\n
无字✔ 箭刻 门风刻 圈风刻 大于五✔ 小于五✔ 全大✔ 全中✔ 全小✔ 清龙[未验证] 花龙[未验证] 组合龙✔\n
全带五✔ 全带幺✔ 全不靠✔ 七星不靠 缺一门✔ 断幺✔ 混幺九✔ 全求人 不求人✔ 推不倒✔\n
门前清✔ 妙手回春 杠上开花 海底捞月 坎张 边张 单钓 和绝张 自摸 清幺九✔\n
十三幺[未验证] 字一色[未验证] 大四喜[未验证] 大三元[未验证] 小四喜 小三元 四暗刻✔ 四杠子 九莲宝灯✔\n
"""


class Score:
    top = 88
    b = 64
    c = 48
    d = 32
    high = 24
    f = 5
    g = 12
    normal = 8
    i = 6
    j = 4
    k = 2
    basic = 1


uni_TIAO = []
uni_TONG = []
uni_WAN = []
uni_WIND = []
uni_ARROW = []
all_uni = []

color_WAN = ['🀇', '🀈', '🀉', '🀊', '🀋', '🀌', '🀍', '🀎', '🀏']
color_TIAO = ['🀐', '🀑', '🀒', '🀓', '🀔', '🀕', '🀖', '🀗', '🀘']
color_TONG = ['🀙', '🀚', '🀛', '🀜', '🀝', '🀞', '🀟', '🀠', '🀡']
cha_WIND = ['🀀', '🀁', '🀂', '🀃']
cha_ARROW = ['🀄', '🀅', '🀆']

color_list = [color_WAN, color_TIAO, color_TONG]

owned = []
cards = []

''' 牌型总列表 '''
orphans = ['🀐', '🀘', '🀇', '🀏', '🀙', '🀡', '🀀', '🀁', '🀂', '🀃', '🀄', '🀅', '🀆']
latern = [['🀇', '🀇', '🀇', '🀈', '🀉', '🀊', '🀋', '🀌', '🀍', '🀎', '🀏', '🀏', '🀏'],
          ['🀐', '🀐', '🀐', '🀑', '🀒', '🀓', '🀔', '🀕', '🀖', '🀗', '🀘', '🀘', '🀘'],
          ['🀙', '🀙', '🀙', '🀚', '🀛', '🀜', '🀝', '🀞', '🀟', '🀠', '🀡', '🀡', '🀡']]
green = ['🀑', '🀒', '🀓', '🀕', '🀗']
orphan_number = ['🀐', '🀘', '🀇', '🀏', '🀙', '🀡']
central_symmetry = ['🀙', '🀚', '🀛', '🀜', '🀝', '🀠', '🀡', '🀑', '🀓', '🀔', '🀕', '🀗', '🀘', '🀆']
all_evens = []
for each in color_WAN:
    if color_WAN.index(each) % 2 == 1:
        all_evens.append(each)
for each in color_TIAO:
    if color_TIAO.index(each) % 2 == 1:
        all_evens.append(each)
for each in color_TONG:
    if color_TONG.index(each) % 2 == 1:
        all_evens.append(each)

''' 排列组合分列表  '''
# 以下列表用于收集手牌的所有排列组合
doublets = []
flush = []
triplets = []
pengs = []
gangs = []
flowers = []
chars = cha_WIND + cha_ARROW

''' 花色序数分列表  '''
# 以下列表用于normal函数中判断花色和序数组合
# all_WIND 和 all_ARROWs 用于 score.py 模块内的番种分数结算
all_WANs = []
all_TIAOs = []
all_TONGs = []
all_WINDs = []
all_ARROWs = []

# 这一部分代码是为了重新安排Java程序中生成的字符串列表，mahjong_data.txt内的格式如下：
#
# 第一行：原手牌
# 第二行：被agariBackTrack.java 组织之后的呈雀头-刻子-顺子序列的手牌
# 第三行：是否自摸
# 第四行：是否海底捞月
# 第五行：是否妙手回春
# 第六行：是否杠上开花。
f = open("mahjong_data.txt", mode="r", encoding="utf-8")
scripts = []
for line in f:
    scripts.append(line.strip("\n".format()))
f.close()

owned = eval(scripts[0])
cards = eval(scripts[1])
pengs = eval(scripts[2])
gangs = eval(scripts[3])

is_zimo = eval(scripts[4])
is_salavge = eval(scripts[5])
is_toSpring = eval(scripts[6])
is_bloomed = eval(scripts[7])

is_sided = eval(scripts[8])
is_single = eval(scripts[9])
is_between = eval(scripts[10])

side_wind = eval(scripts[11])
seat_wind = eval(scripts[12])

# 注意：七对和不靠等大牌需要优先进行判定，这里暂时跳过这一步，直接计算雀头、刻子和顺子
# 以及各种花色中所含牌类的数量：
doublets.append(cards[0])

cards_copy = cards.copy()
del cards_copy[0]

for each in cards_copy:
    for i in range(len(each)):
        if each[i] == each[i + 1]:
            triplets.append(each)
            break
        if ord(each[i]) == ord(each[i + 1]) - 1:
            flush.append(each)
            break

for each in cards:
    if each[0] in color_WAN:
        all_WANs.append(each)

    if each[0] in color_TIAO:
        all_TIAOs.append(each)

    if each[0] in color_TONG:
        all_TONGs.append(each)

    if each[0] in cha_WIND:
        all_WINDs.append(each)

    if each[0] in cha_ARROW:
        all_ARROWs.append(each)

''' 排列组合总列表  '''
# all_groups 用作 normal() 函数手牌数量判定
all_CHARs = []
all_groups = []
all_groups += triplets + flush + pengs + gangs + doublets

colors = color_WAN + color_TIAO + color_TONG
characters = cha_WIND + cha_ARROW
mahjongs = colors + characters


class SevenDoublets:
    victory = False
    ischa = False
    isflush = False

    count = 0
    flushed = []
    score = 24
    s_star = 64
    s_flushing = 88

    name_sd = "七对"
    name_stars = "大七星"
    name_flushed = "连七对"

    def seven_doublets(self, input_owned):
        """这里是七对子的和牌判定，直接判断doublets列表里面是否有 7副对子。
        如果这些对子均为字牌，则返回大七星判定。如果这些对子彼此相连，且为同一花色
        则返回连七对判定。"""
        cha_true = 0
        for each in input_owned:
            if input_owned.count(each) == 2:
                self.count += 1
            if each in chars:
                cha_true += 1

        if self.count == 14 and len(flush) == 4:
            self.isflush = True
            print("{} \t \t{}番".format(self.name_flushed, self.s_flushing))
            return self.s_flushing

        elif self.count == 14:
            self.victory = True
            print("{} \t \t{}番".format(self.name_sd, self.score))
            return self.score

        elif cha_true == 7:
            self.ischa = True
            print("{} \t \t{}番".format(self.name_stars, self.score))
            return self.s_star

        else:
            return 0


class ColorCollections:
    victory = False
    v_mixed = False
    is_tiao = True
    is_tong = True

    num_mix = 0
    num = 0
    score = 24
    score_mix = 6
    name = "清一色"
    name_mix = "混一色"

    def single_colored(self, owned):
        """这里是清一色和牌判定，判断 all_groups 列表里面的所有单个字符均属于
        同一花色，返回清一色判定。"""
        for i in range(len(owned)):
            if owned[i] in color_WAN:
                self.num += 1
                self.is_tong = False
                self.is_tiao = False
            if owned[i] in color_TONG and self.is_tong:
                self.num += 1
                self.is_tiao = False
            if owned[i] in color_TIAO and self.is_tiao:
                self.num += 1

        if self.num == 14:
            self.victory = True
            print("{} \t \t{}番".format(self.name, self.score))
            return self.score
        else:
            return 0

    def mixed_color(self, input_owned):
        for i in range(len(owned)):
            if owned[i] in chars:
                self.num_mix += 1
                self.victory = False
            else:
                self.num_mix = 0
            if owned[i] in color_WAN:
                self.num_mix += 1
                self.is_tong = False
                self.is_tiao = False
            if owned[i] in color_TONG and self.is_tong:
                self.num_mix += 1
                self.is_tiao = False
            if owned[i] in color_TIAO and self.is_tiao:
                self.num_mix += 1

        if self.num_mix == 14:
            self.num = 0
            self.v_mixed = True
            print("{} \t \t{}番".format(self.name_mix, self.score_mix))
            return self.score_mix
        else:
            return 0


# 所有1番和部分2番的牌型判断：
class Normal:
    v_char_less = False
    v_hexa_flush = False
    victory_reunion = False
    victory_quadra = False
    victory_clear = False

    victory_ordinary = False
    v_missing_orphan = False
    v_orphan_flush = False
    v_missing_color = False
    v_triple_same_flush = False

    str_charless = "无字"
    str_hexa = "连六"
    str_reunion = "喜相逢"
    str_quadra = "四归一"
    str_clear = "门前清"

    str_ordinary = "平和"
    str_missing_o = "断幺喵！"
    str_orphan_f = "老少副"
    str_missing_c = "缺一门"
    str_tri = "三色三同顺"

    index = []
    is_orphan = True
    num = 0
    color_count = 0
    score = 1
    score2 = 2
    score3 = 8

    def char_less(self, owned):
        """无字和牌判定，遍历手牌列表，如果元素不属于字牌则数量加一，如果数量等于手牌长度则判定通过。"""
        for each in owned:
            if each not in characters:
                self.num += 1

        if self.num == 14:
            print("{} \t \t{}番".format(self.str_charless, self.score))
            self.v_char_less = True
            return self.score
        else:
            return 0

    def hexa_flush(self, flushes):
        """连六的判定，传入玩家的手牌组合列表cards。查找一种花色是否是索引值相连的六张牌。
        """
        for i in range(len(flushes)):
            if len(flushes) > 1 and ord(flushes[i][0]) + 3 == ord(flushes[i + 1][0]):
                self.v_hexa_flush = True
                print("{} \t \t{}番".format(self.str_hexa, self.score))
                return self.score
            else:
                break

        if not self.v_hexa_flush:
            return 0

    def reunite(self, flushes):
        """喜相逢的判定: 查找两种花色的手牌在花色列表里的索引值，完全相同则判定为真"""
        for each in flushes:
            if each[0] in color_WAN:
                self.index.append(color_WAN.index(each[0]))

            elif each[0] in color_TIAO:
                self.index.append(color_TIAO.index(each[0]))

            elif each[0] in color_TONG:
                self.index.append(color_TONG.index(each[0]))

        for j in self.index:
            if self.index.count(j) == 3:
                self.victory_reunion = True
                print("{} \t \t{}番".format(self.str_reunion, self.score))
                return self.score

            elif self.index.count(j) == 4:
                self.v_triple_same_flush = True
                print("{} \t{}番".format(self.str_tri, self.score3))
                return self.score3

        if not self.victory_reunion:
            return 0
        elif not self.v_triple_same_flush:
            return 0

    def quadra_cards(self, input_owned):
        """四归一的判断，需要传入玩家原手牌，如果有四张牌且玩家未鸣杠则判定有效"""
        for each in input_owned:
            if input_owned.count(each) == 4:
                self.victory_quadra = True
                print("{} \t \t{}番".format(self.str_quadra, self.score))
                return self.score

            else:
                return 0

    def clearing(self):
        """门前清的判断代码，检测碰牌列表和杠牌列表内的元素个数。"""
        if len(pengs) == 0 and len(gangs) == 0:
            self.victory_clear = True
            print("{} \t \t{}番".format(self.str_clear, self.score2))
            return self.score2
        else:
            return 0

    def get_ordinary(self):
        """平和的判定函数，检测flush内元素的个数，同时将牌对子不能是字牌。"""
        if len(flush) == 4 and doublets[0] not in characters:
            self.victory_ordinary = True
            print("{} \t \t{}番".format(self.str_ordinary, self.score2))
            return self.score2
        else:
            return 0

    def missing_orphan(self, owned):
        """断幺的类型，传入玩家原手牌，手牌中没有来自【orphans】——幺九牌列表里的元素"""
        for i in owned:
            if i in orphans:
                self.is_orphan = False
                break
            else:
                self.is_orphan = True
                continue

        if self.is_orphan is True:
            self.v_missing_orphan = True
            print("{} \t{}番".format(self.str_missing_o, self.score2))
            return self.score2
        else:
            return 0

    def orphan_tiles(self, input_flush):
        """老少副的牌型，传入玩家手牌组合。查找手牌中是否有索引值为012或678的顺子。"""
        for each in input_flush:
            for e in orphan_number:
                if len(each) > 2:
                    if each[0] == e or each[2] == e:
                        self.v_orphan_flush = True
                        print("{} \t \t{}番".format(self.str_orphan_f, self.score))
                        return self.score

        if not self.v_orphan_flush:
            return 0

    def missing_color(self, input_cards):
        """缺一门"""
        for each in input_cards:
            if each[0] in color_WAN:
                self.color_count += 1
            if each[0] in color_TIAO:
                self.color_count += 1
            if each[0] in color_TONG:
                self.color_count += 1

        if self.color_count <= 2:
            self.v_missing_color = True
            print("{} \t \t{}番".format(self.str_missing_c, self.score))
            return self.score
        else:
            return 0


class NormalTiles:
    two = False
    three = False
    four = False

    str2 = "一般高"
    str3 = "一色三同顺"
    str4 = "一色四同顺"

    score = 1
    score3 = 24
    score4 = 48

    def normal_height(self, tiles):
        """一般高的和牌判定函数，直接在normal()函数调用后检测顺子中相同元素的个数。"""
        for each in tiles:
            if tiles.count(each) == 2:
                self.two = True
                print("{} \t \t{}番".format(self.str2, self.score))
                return self.score

        if not self.two:
            return 0

    def three_flushes(self, tiles):
        """一色三同顺的判定，需要三幅（花色和序数）完全相同的顺子。"""
        for each in tiles:
            if tiles.count(each) == 3:
                self.three = True
                print("{} \t \t{}番".format(self.str3, self.score3))
                return self.score3
        if not self.three:
            return 0

    def four_flushes(self, tiles):
        """一色四同顺需要四副完全相同的顺子。"""
        for each in tiles:
            if tiles.count(each) == 4:
                self.four = True
                print("{} \t \t{}番".format(self.str4, self.score4))
                return self.score4
        if not self.four:
            return 0


class TypeForm:
    score = 1
    score8 = 8

    str_zimo = "自摸"
    str_salve = "海底捞月"
    str_bloom = "杠上花"
    str_spring = "妙手回春"

    str_side = "边张"
    str_between = "坎张"
    str_single = "单钓"

    def type_form(self, bool_zimo, bool_salve, bool_bloom, bool_spring):
        """自摸，海底捞月，杠上花和妙手回春的判断，直接借用传入的布尔类型数据"""
        if bool_zimo:
            print("{}\t \t\t{}番".format(self.str_zimo, self.score))
            return self.score
        if bool_salve:
            print("{}\t \t{}番".format(self.str_salve, self.score8))
            return self.score8
        if bool_bloom:
            print("{}\t \t{}番".format(self.str_bloom, self.score8))
            return self.score8
        if bool_spring:
            print("{}\t \t{}番".format(self.str_spring, self.score8))
            return self.score8
        else:
            return 0

    def type_and_form(self, bool_side, bool_between, bool_single):
        if bool_side:
            print("{}\t \t{}番".format(self.str_side, self.score))
            return self.score
        if bool_between:
            print("{}\t \t{}番".format(self.str_between, self.score))
            return self.score
        if bool_single:
            print("{}\t \t{}番".format(self.str_single, self.score))
            return self.score
        else:
            return 0


class OrphanTriplets:
    """
    这里是判断幺九牌 + 刻子类的牌型，有 1番的幺九刻，32番的混幺九和 64番的清幺九。
    """
    victory_o = False
    victory_f = False
    victory_m = False

    str_o = "幺九刻"
    str_f = "清幺九"
    str_m = "混幺九"

    orphan_count = 0
    score = 1
    mixed_score = 32
    full_score = 64

    def full_orphan_triplets(self, input_cards):
        """清幺九牌型，传入玩家所有手牌的面子组合cards，判断手牌中是否全部由orphan_number
        列表里面的元素组成的刻子和对子。"""
        self.orphan_count = 0
        for each in input_cards:
            if each[0] in orphan_number:
                self.orphan_count += 1

        if self.orphan_count == 5:
            self.victory_m = True
            print("{:>10} \t \t{:<}番".format(self.str_f, self.full_score))
            return self.full_score
        else:
            return 0

    def mixed_orphan_triplets(self, input_cards):
        """混幺九的牌型，除了判断的列表从orphan_numbers变成了orphans之外没有其它变化。
        所以混幺九的成形张数比清幺九要多。"""
        self.orphan_count = 0
        for each in input_cards:
            if each[0] in orphans:
                self.orphan_count += 1

        if self.orphan_count == 5:
            self.victory_m = True
            print("{:>10} \t \t{:<}番".format(self.str_m, self.mixed_score))
            return self.mixed_score
        else:
            return 0

    def orphan_triplets(self, triplet_list):
        """幺九刻的和牌型，传入triplets这个刻子列表，查找刻子列表中是否有和orphan_number
        列表中相同的元素，只要有一个便判定此番种有效。"""
        for each in triplet_list:
            if each[0] in orphan_number:
                self.victory_o = True
                print("{} \t \t{}番".format(self.str_o, self.score))
                return self.score

        if not self.victory_o:
            return 0


class WindAndArrow:
    victory_f_wind = False
    victory_s_wind = False
    victory_arrow = False
    arrow_triplets = False
    double_arrows = False

    f_wind = "圈风刻"
    s_wind = "门风刻"
    d_arrows = "双箭刻"

    score = 2
    score1 = 6

    def field_wind(self, wind):
        """圈风刻和牌判定，如果面子列表中存在着一个长度为 3 的字符串，如果每个
        字符 == mahjong.fieldWind 则返回圈风刻判定。"""
        for b in all_WINDs:
            if b[0] == wind:
                self.victory_f_wind = True
                print("{}\t \t{}".format(self.f_wind, self.score))
                return self.score
            else:
                return 0

    def side_wind(self, seat):
        """门风刻和牌判定，如果面子列表中存在着一个长度为 3 的字符串，如果每个
        字符 == banker.sideWind 则返回门风刻判定。"""
        for each in all_WINDs:
            if each[0] == seat:
                self.victory_s_wind = True

        if self.victory_s_wind:
            return self.score
        else:
            return 0

    def arrow_triplets(self):
        for each in triplets:
            if each in all_ARROWs:
                if len(each) == 1:
                    self.arrow_triplets = True

                elif len(each) == 2:
                    self.double_arrows = True

        if self.arrow_triplets:
            return self.score
        elif self.double_arrows:
            return self.score
        else:
            return 0


class NumberTriplets:
    two = False
    three = False
    four = False
    same2 = False
    same3 = False
    step3 = False

    str2 = "双暗刻"
    str3 = "三暗刻"
    str4 = "四暗刻"
    str_same2 = "双同刻"
    str_name3 = "三色三同刻"
    str_step3 = "三色三节高"

    index = []
    score = 2
    score2 = 4
    score3 = 16
    score4 = 64

    def double_triplets(self, triplet):
        """双暗刻，检测 triplets列表的长度。"""
        if len(triplet) == 2:
            self.two = True
            print("{} \t \t{}番".format(self.str2, self.score))
            return self.score
        else:
            return 0

    def triple_triplets(self, triplet):
        """三暗刻，同上，只是分数不一样"""
        if len(triplet) == 3:
            self.three = True
            print("{} \t \t{}番".format(self.str3, self.score3))
            return self.score3
        else:
            return 0

    def quadra_triplets(self, input_triplet):
        """四暗刻，同上，不过是役满。"""
        if len(input_triplet) == 4 and input_triplet[len(input_triplet) - 1 != "null"]:
            self.four = True
            print("{} \t \t{}番".format(self.str4, self.score4))
            self.three = False
            return self.score4
        else:
            return 0

    def same_triplets(self, triplet):
        """双同刻和三同刻，逻辑与喜相逢类似，只不过索引的列表变成了triplet"""
        for each in triplet:
            if each[0] in color_WAN:
                self.index.append(color_WAN.index(each[0]))

            elif each[0] in color_TIAO:
                self.index.append(color_TIAO.index(each[0]))

            elif each[0] in color_TONG:
                self.index.append(color_TONG.index(each[0]))

        for j in self.index:
            if self.index.count(j) == 2:
                self.same2 = True
                print("{} \t \t{}番".format(self.str_same2, self.score))
                return self.score
            elif self.index.count(j) == 3:
                self.same3 = True
                print("{} \t{}番".format(self.str_name3, self.score3))
                return self.score3

        if not self.same2:
            return 0
        elif not self.same3:
            return 0

    def stepping_flush3(self, input_cards):
        """三色三节高"""
        for each in input_cards:
            pass


class FullTiles:
    v_orphan = False
    v_mixed = False
    v_fives = False

    str_o = "纯全带幺九"
    str_m = "混全带幺九"
    str_f = "全带五"

    count = 0
    orphan_count = 0
    score_mixed = 2
    score_orphan = 4
    score_five = 16

    def mixed_orphan_tiles(self, input_owned):
        """混全带幺九：日麻特有的混合番种，只是将第二个嵌套的迭代循环(便乘)orphans即可。(确信)"""
        for each in input_owned:
            for e in orphans:
                if each[0] == e or each[2] == e:
                    self.v_mixed = True
                    print("{} \t{}番".format(self.str_m, self.score_mixed))
                    return self.score_mixed

        if not self.v_mixed:
            return 0

    def full_orphan_tiles(self, input_owned):
        """全带幺：传入玩家手牌组合【cards】列表。查找刻子、顺子、对子中是否均存在索引
        值为0或者8的牌。"""
        for each in input_owned:
            if len(each) == 1:
                for e in orphan_number:
                    if each == e:
                        self.orphan_count += 1
            for i in each:
                if len(each) > 1:
                    for e in orphan_number:
                        if i == e:
                            self.orphan_count += 1

        if self.orphan_count == 5:
            print("{} \t{}番".format(self.str_o, self.score_orphan))
            return self.score_orphan
        else:
            return 0

    def all_five(self, input_owned):
        """全带五在这里进行判断，需要传入玩家的手牌面子组合。判断当cards列表元素的长度为 1
        的时候，这时这个元素是手牌的面子，接着查找colors里面的第4、第13和第21个索引的元素，
        即五万、五条和五饼是否与其相同。刻子类似，但是顺子比较复杂，我们需要再去迭代每个组合
        内部的元素，如果这些组合中都存在五万、五条和五饼，那么才算判断有效。"""
        for each in input_owned:
            if len(each) == 1:
                if each == colors[4] or each == colors[13] or each == colors[21]:
                    self.count += 1
            for i in each:
                if len(each) > 1:
                    if i == colors[4] or i == colors[13] or i == colors[21]:
                        self.count += 1

        if self.count == 5:
            self.v_fives = True
            print("{} \t \t{}番".format(self.str_f, self.score_five))
            return self.score_five
        else:
            return 0


class PengPeng:
    victory = False
    name = "碰碰和"
    score = 6

    def peng_peng(self):
        """碰碰和和牌判定，如果玩家曾鸣牌食碰，食杠或食和，且刻子列表和碰牌列表
        的总长度为 4 则返回碰碰和判定。"""
        if len(pengs) + len(triplets) == 4:
            self.victory = True
            print("{} \t \t{}番".format(self.name, self.score))
            return self.score
        else:
            return 0


class SteppingFlush:
    v_three_steps = False
    v_single_triple_steps = False
    v_single_quadra_steps = False

    str_step3 = "三色三步高"
    str_single3 = "一色三步高"
    str_single4 = "一色四步高"

    step3_list = []
    score1 = 6
    score2 = 16
    score3 = 48

    def three_steps(self, input_flush):
        """三色三步高（国标版断幺）：查找三种花色的手牌顺子的第一张在花色列表里的索引值，
        三张牌索引值相连则为真"""
        st_count = 0
        for i in range(len(input_flush)):
            for j in input_flush[i]:
                if len(input_flush[i]) > 2 and j in color_WAN:
                    self.step3_list.append(color_WAN.index(j))
                    break
                if len(input_flush[i]) > 2 and j in color_TIAO:
                    self.step3_list.append(color_TIAO.index(j))
                    break
                if len(input_flush[i]) > 2 and j in color_TONG:
                    self.step3_list.append(color_TONG.index(j))
                    break

        if len(self.step3_list) > 3:
            if self.step3_list[0] + 2 == self.step3_list[1] or \
                    self.step3_list[0] + 2 == self.step3_list[2] or \
                    self.step3_list[0] + 2 == self.step3_list[3]:
                st_count += 1

            if self.step3_list[1] + 2 == self.step3_list[2] or \
                    self.step3_list[1] + 2 == self.step3_list[3]:
                st_count += 1

            if self.step3_list[2] + 2 == self.step3_list[3]:
                st_count += 1

        if st_count == 2:
            self.v_three_steps = True
            print("{} \t{}番".format(self.str_step3, self.score1))
            return self.score1
        else:
            return 0


class Evens:
    score = 24
    victory = False
    str = "全双刻"
    even_count = 0

    def all_evens(self, input_owned):
        for each in input_owned:
            if each in all_evens:
                self.even_count += 1
            else:
                self.even_count = 0

        if self.even_count == 14:
            print("{}\t \t{}番".format(self.str, self.score))
            return self.score
        else:
            return 0


class PureStraight():
    v_dragon = False
    v_mixed = False
    v_combined = False

    str_dragon = "清龙"
    str_mixed = "花龙"
    str_combined = "组合龙"
    mix_list = []
    comb_list = []
    mix_count = 0

    count = 0
    score = 16
    score2 = 12
    score3 = 8

    def dragon(self, owned):
        tiao = True
        tong = True
        for each in owned:
            if each in color_WAN:
                self.comb_list.append(color_WAN.index(each))
                tiao = False
                tong = False
            if each in color_TIAO and tiao:
                self.comb_list.append(color_TIAO.index(each))
                tong = False
            if each in color_TONG and tong:
                self.comb_list.append(color_TONG.index(each))

        self.comb_list.sort()
        for i in range(len(self.mix_list)):
            if i == self.mix_list[i]:
                self.comb_list += 1
        if len(self.comb_list) == 9 and cards[len(cards) - 1 != "null"]:
            self.v_combined = True
            print("{} \t \t{}番".format(self.str_dragon, self.score3))
            return self.score
        else:
            return 0

    def mixed_dragon(self, input_cards):
        for i in range(len(input_cards)):
            if len(input_cards[i]) > 2 and ord(input_cards[i]) + 3 == 0:
                pass

    def combined_dragon(self, input_owned):
        for each in input_owned:
            if each in color_WAN:
                self.comb_list.append(color_WAN.index(each))
            if each in color_TIAO:
                self.comb_list.append(color_TIAO.index(each))
            if each in color_TONG:
                self.comb_list.append(color_TONG.index(each))

        self.comb_list.sort()
        for i in range(len(self.mix_list)):
            if i == self.mix_list[i]:
                self.comb_list += 1
        if len(self.comb_list) == 9 and cards[len(cards) - 1 != "null"]:
            self.v_combined = True
            print("{} \t \t{}番".format(self.str_combined, self.score3))
            return self.score
        else:
            return 0


class Dependence:
    victory = False
    score = 2
    independence = False

    def dependence(self, banging=None):
        """不求人和牌方法，如果玩家在没有任何碰牌或杠牌的情况下自摸和牌算不求人，
        如果玩家在碰、杠四次的情况下放炮点和，则算作全求人。"""
        if len(pengs) == 0 and len(gangs) == 0 and banging == None:
            self.victory = True
        elif len(pengs) + len(gangs) == 4 and not banging:
            self.independence = True


def get_gang():
    if len(gangs):
        _gang = True
        return _gang


class FiveVarieties:
    count = 0
    victory = False
    name = "五门齐"

    is_character = True
    is_bamboo = True
    is_dot = True
    is_wind = True
    is_dragon = True
    score = 6

    def five_varies(self, input_card):
        """五门齐，跟清一色反过来的判定。"""
        for i in input_card:
            if i[0] in color_WAN and self.is_character:
                self.count += 1
                self.is_character = False

            if i[0] in color_TIAO and self.is_bamboo:
                self.count += 1
                self.is_bamboo = False

            if i[0] in color_TONG and self.is_dot:
                self.count += 1
                self.is_dot = False

            if i[0] in cha_WIND and self.is_wind:
                self.count += 1
                self.is_wind = False

            if i[0] in cha_ARROW and self.is_dragon:
                self.count += 1
                self.is_dragon = False

        if self.count == 5:
            self.victory = True
            print("{} \t \t{}番".format(self.name, self.score))
            return self.score
        else:
            return 0


class Symmetry:
    victory_sym = False
    victory_even = False
    sym_count = 0
    even_count = 0
    score = 8
    score2 = 24
    name_sym = "推不倒"
    name_even = "全双刻"

    def get_symmetry(self, input_owned):
        for each in input_owned:
            if each in central_symmetry:
                self.sym_count += 1
            else:
                self.sym_count = 0

        if self.sym_count == 14:
            self.victory_sym = True
            print("{} \t \t{}番".format(self.name_sym, self.score))
            return self.score
        else:
            return 0

    def evens(self, input_owned):
        for each in input_owned:
            if each in all_evens:
                self.even_count += 1
            else:
                self.even_count = 0

        if self.even_count == 14:
            self.victory_even = True
            print("{} \t \t{}番".format(self.name_even, self.score2))
            return self.score2
        else:
            return 0

class NumberSize:
    victory_small = False
    victory_middle = False
    victory_big = False
    v_bigger5 = False
    v_smaller5 = False

    score = 24
    score2 = 12

    str_big = "全大"
    str_mid = "全中"
    str_small = "全小"
    str_big5 = "大于五"
    str_small5 = "小于五"

    small_count = 0
    middel_count = 0
    big_count = 0
    big_count5 = 0
    small_count5 = 0

    def all_big(self, input_owned):
        for each in input_owned:
            if each in color_WAN and color_WAN.index(each) > 5:
                self.big_count += 1
            elif each in color_TIAO and color_TIAO.index(each) > 5:
                self.big_count += 1
            elif each in color_TONG and color_TONG.index(each) > 5:
                self.big_count += 1

        if self.big_count == 14:
            self.victory_big = True
            print("{} \t \t{}番".format(self.str_big, self.score))
            return self.score
        else:
            return 0

    def all_middle(self, input_owned):
        for each in input_owned:
            if each in color_WAN and 2 < color_WAN.index(each) < 6:
                self.middel_count += 1
            elif each in color_TIAO and 2 < color_TIAO.index(each) < 6:
                self.middel_count += 1
            elif each in color_TONG and 2 < color_TONG.index(each) < 6:
                self.middel_count += 1

        if self.middel_count == 14:
            self.victory_middle = True
            print("{} \t \t{}番".format(self.str_mid, self.score))
            return self.score
        else:
            return 0

    def all_small(self, input_owned):
        for each in input_owned:
            if each in color_WAN and color_WAN.index(each) < 3:
                self.small_count += 1
            elif each in color_TIAO and color_TIAO.index(each) < 3:
                self.small_count += 1
            elif each in color_TONG and color_TONG.index(each) < 3:
                self.small_count += 1

        if self.small_count == 14:
            self.victory_small = True
            print("{} \t \t{}番".format(self.str_small, self.score))
            return self.score
        else:
            return 0

    def bigger5(self, input_owned):
        for each in input_owned:
            if each in color_WAN and color_WAN.index(each) > 4:
                self.big_count5 += 1
            elif each in color_TIAO and color_TIAO.index(each) > 4:
                self.big_count5 += 1
            elif each in color_TONG and color_TONG.index(each) > 4:
                self.big_count5 += 1

        if self.big_count5 == 14:
            self.v_bigger5 = True
            print("{} \t \t{}番".format(self.str_big5, self.score2))
            return self.score2
        else:
            return 0

    def smaller5(self, input_owned):
        for each in input_owned:
            if each in color_WAN and color_WAN.index(each) < 4:
                self.small_count5 += 1
            elif each in color_TIAO and color_TIAO.index(each) < 4:
                self.small_count5 += 1
            elif each in color_TONG and color_TONG.index(each) < 4:
                self.small_count5 += 1

        if self.small_count5 == 14:
            self.v_smaller5 = True
            print("{} \t \t{}番".format(self.str_small5, self.score2))
            return self.score2
        else:
            return 0


class Thirteen_Alones:
    score = 88
    num = 0
    name = "十三幺"
    victory = False

    def thirteen_alones(self, owned):
        if len(owned) == 14:
            orphans.sort()
            for i in orphans:
                if 1 <= owned.count(i) <= 2:
                    self.num += 1

            if self.num == 13:
                self.victory = True
                print("{} \t \t{}".format(self.name, self.score))
            else:
                return 0


class FullGreen:
    victory = False
    score = 88
    name = "绿一色"
    count = 0

    def full_green(self, input_owned):
        for each in input_owned:
            if each in green:
                self.count += 1

        if self.count == 14:
            self.victory = True
            print("{} \t \t{}".format(self.name, self.score))
            return self.score
        else:
            return 0


class LotusLantern:
    """九莲宝灯的判定代码。对于传入的手牌 owned，循环九莲宝灯列表 lantern 内的各元素，向列表内的列表
    追加各花色内 1~9 的序数牌，然后将其与手牌进行比对，完全匹配即返回 True，否则返回 False。"""
    victory = False
    name = "九莲宝灯"
    score = 88

    def lotus_lantern(self, owned):
        for each in latern:
            for i in range(len(owned)):
                self.each_one = each.copy()

                if each[0] in color_WAN and owned[i] in color_WAN:
                    self.each_one.append(owned[i])
                    self.each_one.sort()
                    if owned == self.each_one:
                        self.victory = True

                if each[0] in color_TONG and owned[i] in color_TONG:
                    self.each_one.append(owned[i])
                    self.each_one.sort()
                    if owned == self.each_one:
                        self.victory = True

                if each[0] in color_TIAO and owned[i] in color_TIAO:
                    self.each_one.append(owned[i])
                    self.each_one.sort()
                    if owned == self.each_one:
                        self.victory = True

        if self.victory:
            print("{} \t{}番".format(self.name, self.score))
            return self.score
        else:
            return 0


class GreatTriplets:
    """大三元的判定"""
    score = 88
    name = "大三元"
    victory = False

    def the_great_triplets(self, owned):
        if owned.count(cha_ARROW[0]) >= 3 and owned.count(cha_ARROW[1]) >= 3 and \
                owned.count(cha_ARROW[2]) >= 3:
            self.victory = True
            print("{} \t \t{}番".format(self.name, self.score))
            return self.score
        else:
            return 0


class GreatQuadrilets:
    score = 88
    count = 0
    name1 = "大四喜"
    name2 = "小四喜"
    victory = False

    def the_great_quadrilets(self, owned):
        for i in cha_WIND:
            if i in owned and owned.count(i) >= 3:
                self.count += 1
        if self.count == 4:
            self.victory = True
            print("{} \t \t{}番".format(self.name1, self.score))
            return self.score

        else:
            return 0

    def the_little_quadrilets(self):
        if owned.count(cha_WIND[0]) == 2 or owned.count(cha_WIND[1]) == 2 or \
                owned.count(cha_WIND[2]) == 2 or owned.count(cha_WIND[3]) == 2:

            if owned.count(cha_WIND[0]) >= 3 and owned.count(cha_WIND[1]) >= 3 and \
                    owned.count(cha_WIND[2]) >= 3:
                self.score = 64
                self.victory = True

            elif owned.count(cha_WIND[0]) >= 3 and owned.count(cha_WIND[2]) >= 3 and \
                    owned.count(cha_WIND[3]) >= 3:
                self.score = 64
                self.victory = True

            elif owned.count(cha_WIND[1]) >= 3 and owned.count(cha_WIND[2]) >= 3 and \
                    owned.count(cha_WIND[3]) >= 3:
                self.score = 64
                self.victory = True


class ColorCharacters:
    victory = False
    num = 0
    score = 64

    def character_Colors(self, owned):
        for each in owned:
            if each in characters:
                self.num += 1

            if self.num == 14:
                self.victory = True


def four_Gangs():
    if len(gangs) == 4:
        return True


doubles = 0

normal = Normal()
nt = NormalTiles()
tf = TypeForm()
num_tri = NumberTriplets()
ft = FullTiles()
se = Symmetry()

or_tri = OrphanTriplets()
seven = SevenDoublets()
sc = ColorCollections()
fv = FiveVarieties()
pp = PengPeng()

num_size = NumberSize()
lotus = LotusLantern()
tgt = GreatTriplets()
gq = GreatQuadrilets()
ta = Thirteen_Alones()

ps = PureStraight()
sf = SteppingFlush()
wda = WindAndArrow()

doubles += normal.char_less(owned)
doubles += normal.hexa_flush(flush)
doubles += tf.type_form(is_zimo, is_salavge, is_bloomed, is_toSpring)
doubles += tf.type_and_form(is_sided, is_between, is_single)

doubles += normal.orphan_tiles(cards)
doubles += normal.quadra_cards(owned)
doubles += normal.reunite(flush)

doubles += or_tri.orphan_triplets(triplets)
doubles += or_tri.mixed_orphan_triplets(triplets)
doubles += or_tri.full_orphan_triplets(triplets)

doubles += normal.clearing()
doubles += normal.get_ordinary()
doubles += normal.missing_orphan(owned)
doubles += normal.missing_color(cards)

#doubles += wda.field_wind(side_wind)
doubles += wda.side_wind(seat_wind)

doubles += nt.normal_height(flush)
doubles += nt.three_flushes(flush)
doubles += nt.four_flushes(flush)

doubles += sf.three_steps(flush)

doubles += num_tri.double_triplets(triplets)
doubles += num_tri.same_triplets(triplets)
doubles += num_tri.triple_triplets(triplets)
doubles += num_tri.quadra_triplets(triplets)

doubles += sc.mixed_color(owned)
doubles += ps.combined_dragon(owned)

doubles += ft.full_orphan_tiles(cards)
# doubles += ft.mixed_orphan_tiles(owned)
doubles += ft.all_five(cards)

doubles += fv.five_varies(cards)
doubles += sc.single_colored(owned)
doubles += pp.peng_peng()

doubles += se.get_symmetry(owned)
doubles += se.evens(owned)

doubles += num_size.all_big(owned)
doubles += num_size.all_middle(owned)
doubles += num_size.all_small(owned)

doubles += lotus.lotus_lantern(owned)
doubles += tgt.the_great_triplets(owned)
doubles += gq.the_great_quadrilets(owned)
doubles += ta.thirteen_alones(owned)

doubles += seven.seven_doublets(owned)

# 在这里将番种追加写入txt文件
file = open("mahjong_data.txt", mode="a", encoding="utf-8")

if normal.v_char_less:
    file.write(normal.str_charless + "," + str(normal.score) + "\n")

if normal.v_hexa_flush:
    file.write(normal.str_hexa + "," + str(normal.score) + "\n")

if is_zimo:
    file.write(tf.str_zimo + "," + str(tf.score) + "\n")
if is_sided:
    file.write(tf.str_side + "," + str(tf.score) + "\n")
if is_between:
    file.write(tf.str_between + "," + str(tf.score) + "\n")
if is_single:
    file.write(tf.str_single + "," + str(tf.score) + "\n")

if wda.victory_f_wind:
    file.write(wda.f_wind + "," + str(wda.score) + "\n")
if wda.victory_s_wind:
    file.write(wda.s_wind + "," + str(wda.score) + "\n")
if wda.victory_arrow:
    file.write(wda.d_arrows + "," + str(wda.d_arrows) + "\n")

if normal.v_orphan_flush:
    file.write(normal.str_orphan_f + "," + str(normal.score) + "\n")

if normal.v_missing_color:
    file.write(normal.str_missing_c + "," + str(normal.score) + "\n")

if normal.victory_reunion:
    file.write(normal.str_reunion + "," + str(normal.score) + "\n")

if normal.victory_quadra:
    file.write(normal.str_quadra + "," + str(normal.score) + "\n")

if normal.victory_clear:
    file.write(normal.str_clear + "," + str(normal.score2) + "\n")

if normal.victory_ordinary:
    file.write(normal.str_ordinary + "," + str(normal.score2) + "\n")

if normal.v_missing_orphan:
    file.write(normal.str_missing_o + "," + str(normal.score2) + "\n")

if nt.two:
    file.write(nt.str2 + "," + str(nt.score) + "\n")
if nt.three:
    file.write(nt.str3 + "," + str(nt.score3) + "\n")
if nt.four:
    file.write(nt.str4 + "," + str(nt.score4) + "\n")

if sf.v_three_steps:
    file.write(sf.str_step3 + "," + str(sf.score1) + "\n")
elif sf.v_single_triple_steps:
    file.write(sf.str_single3 + "," + str(sf.score2) + "\n")
elif sf.v_single_quadra_steps:
    file.write(sf.str_single4 + "," + str(sf.score3) + "\n")

if num_tri.two:
    file.write(num_tri.str2 + "," + str(num_tri.score) + "\n")
if num_tri.three:
    file.write(num_tri.str3 + "," + str(num_tri.score3) + "\n")
if num_tri.four:
    file.write(num_tri.str4 + "," + str(num_tri.score4) + "\n")
if num_tri.same2:
    file.write(num_tri.str_same2 + "," + str(num_tri.score) + "\n")
if num_tri.same3:
    file.write(num_tri.str_name3 + "," + str(num_tri.score3) + "\n")
if num_tri.step3:
    file.write(num_tri.str_step3 + "," + str(num_tri.score3) + "\n")

if ft.v_orphan:
    file.write(ft.str_o + "," + str(ft.score_orphan) + "\n")
if ft.v_fives:
    file.write(ft.str_f + "," + str(ft.score_five) + "\n")
if ft.v_mixed:
    file.write(ft.str_m + "," + str(ft.score_mixed) + "\n")

if or_tri.victory_o:
    file.write(or_tri.str_o + "," + str(or_tri.score) + "\n")
if or_tri.victory_m:
    file.write(or_tri.str_m + "," + str(or_tri.mixed_score) + "\n")
if or_tri.victory_f:
    file.write(or_tri.str_f + "," + str(or_tri.full_score) + "\n")

if seven.victory:
    file.write(seven.name_sd + "," + str(seven.score) + "\n")
elif seven.ischa:
    file.write(seven.name_stars + "," + str(seven.s_star) + "\n")
elif seven.isflush:
    file.write(seven.name_flushed + "," + str(seven.s_flushing) + "\n")

if sc.victory:
    file.write(sc.name + "," + str(sc.score) + "\n")
if sc.v_mixed:
    file.write(sc.name_mix + "," + str(sc.score_mix) + "\n")

if fv.victory:
    file.write(fv.name + "," + str(fv.score) + "\n")

if pp.victory:
    file.write(pp.name + "," + str(pp.score) + "\n")

if ps.v_dragon:
    file.write(ps.str_dragon + "," + str(ps.score) + "\n")
elif ps.v_mixed:
    file.write(ps.str_mixed + "," + str(ps.score2) + "\n")
elif ps.v_combined:
    file.write(ps.str_combined + "," + str(ps.score3) + "\n")

if num_size.v_bigger5:
    file.write(num_size.str_big5 + "," + str(num_size.score2) + "\n")
if num_size.v_smaller5:
    file.write(num_size.str_small5 + "," + str(num_size.score2) + "\n")
if num_size.victory_big:
    file.write(num_size.str_big + "," + str(num_size.score) + "\n")
if num_size.victory_middle:
    file.write(num_size.str_mid + "," + str(num_size.score) + "\n")
if num_size.victory_small:
    file.write(num_size.str_small + "," + str(num_size.score) + "\n")

if lotus.victory:
    file.write(lotus.name + "," + str(lotus.score) + "\n")

if tgt.victory:
    file.write(tgt.name + "," + str(tgt.score) + "\n")

if gq.victory:
    file.write(gq.name1 + "," + str(gq.score) + "\n")

if ta.victory:
    file.write(ta.name + "," + str(ta.score) + "\n")

if doubles == 0:
    str_no = "无番和"
    print("{}8番".format(str_no) + "\n")
    doubles += 8


file.close()
print(doubles)
