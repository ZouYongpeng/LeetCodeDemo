### ðº è§é¢é¢è§£  
![19. å é¤é¾è¡¨çåæ°ç¬¬Nä¸ªèç¹.mp4](801f6caa-3449-4117-885d-6dd33c117534)

### ð æå­é¢è§£
#### åè¨

å¨å¯¹é¾è¡¨è¿è¡æä½æ¶ï¼ä¸ç§å¸¸ç¨çæå·§æ¯æ·»å ä¸ä¸ªåèç¹ï¼dummy nodeï¼ï¼å®ç ![\textit{next} ](./p__textit{next}_.png)  æéæåé¾è¡¨çå¤´èç¹ãè¿æ ·ä¸æ¥ï¼æä»¬å°±ä¸éè¦å¯¹å¤´èç¹è¿è¡ç¹æ®çå¤æ­äºã

ä¾å¦ï¼å¨æ¬é¢ä¸­ï¼å¦ææä»¬è¦å é¤èç¹ *y*ï¼æä»¬éè¦ç¥éèç¹ *y* çåé©±èç¹ *x*ï¼å¹¶å° *x* çæéæå *y* çåç»§èç¹ãä½ç±äºå¤´èç¹ä¸å­å¨åé©±èç¹ï¼å æ­¤æä»¬éè¦å¨å é¤å¤´èç¹æ¶è¿è¡ç¹æ®å¤æ­ãä½å¦ææä»¬æ·»å äºåèç¹ï¼é£ä¹å¤´èç¹çåé©±èç¹å°±æ¯åèç¹æ¬èº«ï¼æ­¤æ¶æä»¬å°±åªéè¦èèéç¨çæåµå³å¯ã

ç¹å«å°ï¼å¨æäºè¯­è¨ä¸­ï¼ç±äºéè¦èªè¡å¯¹åå­è¿è¡ç®¡çãå æ­¤å¨å®éçé¢è¯ä¸­ï¼å¯¹äºãæ¯å¦éè¦éæ¾è¢«å é¤èç¹å¯¹åºçç©ºé´ãè¿ä¸é®é¢ï¼æä»¬éè¦åé¢è¯å®è¿è¡ç§¯æçæ²éä»¥è¾¾æä¸è´ãä¸é¢çä»£ç ä¸­é»è®¤ä¸éæ¾ç©ºé´ã

#### æ¹æ³ä¸ï¼è®¡ç®é¾è¡¨é¿åº¦

**æè·¯ä¸ç®æ³**

ä¸ç§å®¹ææ³å°çæ¹æ³æ¯ï¼æä»¬é¦åä»å¤´èç¹å¼å§å¯¹é¾è¡¨è¿è¡ä¸æ¬¡éåï¼å¾å°é¾è¡¨çé¿åº¦ *L*ãéåæä»¬åä»å¤´èç¹å¼å§å¯¹é¾è¡¨è¿è¡ä¸æ¬¡éåï¼å½éåå°ç¬¬ *L-n+1* ä¸ªèç¹æ¶ï¼å®å°±æ¯æä»¬éè¦å é¤çèç¹ã

> ä¸ºäºä¸é¢ç®ä¸­ç *n* ä¿æä¸è´ï¼èç¹çç¼å·ä» *1* å¼å§ï¼å¤´èç¹ä¸ºç¼å· *1* çèç¹ã

ä¸ºäºæ¹ä¾¿å é¤æä½ï¼æä»¬å¯ä»¥ä»åèç¹å¼å§éå *L-n+1* ä¸ªèç¹ãå½éåå°ç¬¬ *L-n+1* ä¸ªèç¹æ¶ï¼**å®çä¸ä¸ä¸ªèç¹**å°±æ¯æä»¬éè¦å é¤çèç¹ï¼è¿æ ·æä»¬åªéè¦ä¿®æ¹ä¸æ¬¡æéï¼å°±è½å®æå é¤æä½ã

![p1](https://assets.leetcode-cn.com/solution-static/19/p1.png)

**ä»£ç **

```C++ [sol1-C++]
class Solution {
public:
    int getLength(leetcode.editor.cn.base.ListNode* head) {
        int length = 0;
        while (head) {
            ++length;
            head = head->next;
        }
        return length;
    }

    leetcode.editor.cn.base.ListNode* removeNthFromEnd(leetcode.editor.cn.base.ListNode* head, int n) {
        leetcode.editor.cn.base.ListNode* dummy = new leetcode.editor.cn.base.ListNode(0, head);
        int length = getLength(head);
        leetcode.editor.cn.base.ListNode* cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur->next;
        }
        cur->next = cur->next->next;
        leetcode.editor.cn.base.ListNode* ans = dummy->next;
        delete dummy;
        return ans;
    }
};
```

```Java [sol1-Java]
class Solution {
    public leetcode.editor.cn.base.ListNode removeNthFromEnd(leetcode.editor.cn.base.ListNode head, int n) {
        leetcode.editor.cn.base.ListNode dummy = new leetcode.editor.cn.base.ListNode(0, head);
        int length = getLength(head);
        leetcode.editor.cn.base.ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        leetcode.editor.cn.base.ListNode ans = dummy.next;
        return ans;
    }

    public int getLength(leetcode.editor.cn.base.ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }
}
```

```Python [sol1-Python3]
class Solution:
    def removeNthFromEnd(self, head: leetcode.editor.cn.base.ListNode, n: int) -> leetcode.editor.cn.base.ListNode:
        def getLength(head: leetcode.editor.cn.base.ListNode) -> int:
            length = 0
            while head:
                length += 1
                head = head.next
            return length
        
        dummy = leetcode.editor.cn.base.ListNode(0, head)
        length = getLength(head)
        cur = dummy
        for i in range(1, length - n + 1):
            cur = cur.next
        cur.next = cur.next.next
        return dummy.next
```

```Golang [sol1-Golang]
func getLength(head *leetcode.editor.cn.base.ListNode) (length int) {
    for ; head != nil; head = head.Next {
        length++
    }
    return
}

func removeNthFromEnd(head *leetcode.editor.cn.base.ListNode, n int) *leetcode.editor.cn.base.ListNode {
    length := getLength(head)
    dummy := &leetcode.editor.cn.base.ListNode{0, head}
    cur := dummy
    for i := 0; i < length-n; i++ {
        cur = cur.Next
    }
    cur.Next = cur.Next.Next
    return dummy.Next
}
```

```C [sol1-C]
int getLength(struct leetcode.editor.cn.base.ListNode* head) {
    int length = 0;
    while (head) {
        ++length;
        head = head->next;
    }
    return length;
}

struct leetcode.editor.cn.base.ListNode* removeNthFromEnd(struct leetcode.editor.cn.base.ListNode* head, int n) {
    struct leetcode.editor.cn.base.ListNode* dummy = malloc(sizeof(struct leetcode.editor.cn.base.ListNode));
    dummy->val = 0, dummy->next = head;
    int length = getLength(head);
    struct leetcode.editor.cn.base.ListNode* cur = dummy;
    for (int i = 1; i < length - n + 1; ++i) {
        cur = cur->next;
    }
    cur->next = cur->next->next;
    struct leetcode.editor.cn.base.ListNode* ans = dummy->next;
    free(dummy);
    return ans;
}
```

**å¤æåº¦åæ**

- æ¶é´å¤æåº¦ï¼*O(L)*ï¼å¶ä¸­ *L* æ¯é¾è¡¨çé¿åº¦ã

- ç©ºé´å¤æåº¦ï¼*O(1)*ã

#### æ¹æ³äºï¼æ 

**æè·¯ä¸ç®æ³**

æä»¬ä¹å¯ä»¥å¨éåé¾è¡¨çåæ¶å°ææèç¹ä¾æ¬¡å¥æ ãæ ¹æ®æ ãåè¿ååºãçååï¼æä»¬å¼¹åºæ çç¬¬ *n* ä¸ªèç¹å°±æ¯éè¦å é¤çèç¹ï¼å¹¶ä¸ç®åæ é¡¶çèç¹å°±æ¯å¾å é¤èç¹çåé©±èç¹ãè¿æ ·ä¸æ¥ï¼å é¤æä½å°±åå¾ååæ¹ä¾¿äºã

 ![ppt1](https://assets.leetcode-cn.com/solution-static/19/1.png) ![ppt2](https://assets.leetcode-cn.com/solution-static/19/2.png) ![ppt3](https://assets.leetcode-cn.com/solution-static/19/3.png) ![ppt4](https://assets.leetcode-cn.com/solution-static/19/4.png) ![ppt5](https://assets.leetcode-cn.com/solution-static/19/5.png) ![ppt6](https://assets.leetcode-cn.com/solution-static/19/6.png) ![ppt7](https://assets.leetcode-cn.com/solution-static/19/7.png) ![ppt8](https://assets.leetcode-cn.com/solution-static/19/8.png) ![ppt9](https://assets.leetcode-cn.com/solution-static/19/9.png) ![ppt10](https://assets.leetcode-cn.com/solution-static/19/10.png) 

**ä»£ç **

```C++ [sol2-C++]
class Solution {
public:
    leetcode.editor.cn.base.ListNode* removeNthFromEnd(leetcode.editor.cn.base.ListNode* head, int n) {
        leetcode.editor.cn.base.ListNode* dummy = new leetcode.editor.cn.base.ListNode(0, head);
        stack<leetcode.editor.cn.base.ListNode*> stk;
        leetcode.editor.cn.base.ListNode* cur = dummy;
        while (cur) {
            stk.push(cur);
            cur = cur->next;
        }
        for (int i = 0; i < n; ++i) {
            stk.pop();
        }
        leetcode.editor.cn.base.ListNode* prev = stk.top();
        prev->next = prev->next->next;
        leetcode.editor.cn.base.ListNode* ans = dummy->next;
        delete dummy;
        return ans;
    }
};
```

```Java [sol2-Java]
class Solution {
    public leetcode.editor.cn.base.ListNode removeNthFromEnd(leetcode.editor.cn.base.ListNode head, int n) {
        leetcode.editor.cn.base.ListNode dummy = new leetcode.editor.cn.base.ListNode(0, head);
        Deque<leetcode.editor.cn.base.ListNode> stack = new LinkedList<leetcode.editor.cn.base.ListNode>();
        leetcode.editor.cn.base.ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        leetcode.editor.cn.base.ListNode prev = stack.peek();
        prev.next = prev.next.next;
        leetcode.editor.cn.base.ListNode ans = dummy.next;
        return ans;
    }
}
```

```Python [sol2-Python3]
class Solution:
    def removeNthFromEnd(self, head: leetcode.editor.cn.base.ListNode, n: int) -> leetcode.editor.cn.base.ListNode:
        dummy = leetcode.editor.cn.base.ListNode(0, head)
        stack = list()
        cur = dummy
        while cur:
            stack.append(cur)
            cur = cur.next
        
        for i in range(n):
            stack.pop()

        prev = stack[-1]
        prev.next = prev.next.next
        return dummy.next
```

```Golang [sol2-Golang]
func removeNthFromEnd(head *leetcode.editor.cn.base.ListNode, n int) *leetcode.editor.cn.base.ListNode {
    nodes := []*leetcode.editor.cn.base.ListNode{}
    dummy := &leetcode.editor.cn.base.ListNode{0, head}
    for node := dummy; node != nil; node = node.Next {
        nodes = append(nodes, node)
    }
    prev := nodes[len(nodes)-1-n]
    prev.Next = prev.Next.Next
    return dummy.Next
}
```

```C [sol2-C]
struct Stack {
    struct leetcode.editor.cn.base.ListNode* val;
    struct Stack* next;
};

struct leetcode.editor.cn.base.ListNode* removeNthFromEnd(struct leetcode.editor.cn.base.ListNode* head, int n) {
    struct leetcode.editor.cn.base.ListNode* dummy = malloc(sizeof(struct leetcode.editor.cn.base.ListNode));
    dummy->val = 0, dummy->next = head;
    struct Stack* stk = NULL;
    struct leetcode.editor.cn.base.ListNode* cur = dummy;
    while (cur) {
        struct Stack* tmp = malloc(sizeof(struct Stack));
        tmp->val = cur, tmp->next = stk;
        stk = tmp;
        cur = cur->next;
    }
    for (int i = 0; i < n; ++i) {
        struct Stack* tmp = stk->next;
        free(stk);
        stk = tmp;
    }
    struct leetcode.editor.cn.base.ListNode* prev = stk->val;
    prev->next = prev->next->next;
    struct leetcode.editor.cn.base.ListNode* ans = dummy->next;
    free(dummy);
    return ans;
}
```

**å¤æåº¦åæ**

- æ¶é´å¤æåº¦ï¼*O(L)*ï¼å¶ä¸­ *L* æ¯é¾è¡¨çé¿åº¦ã

- ç©ºé´å¤æåº¦ï¼*O(L)*ï¼å¶ä¸­ *L* æ¯é¾è¡¨çé¿åº¦ãä¸»è¦ä¸ºæ çå¼éã

#### æ¹æ³ä¸ï¼åæé

**æè·¯ä¸ç®æ³**

æä»¬ä¹å¯ä»¥å¨ä¸é¢å¤çåºé¾è¡¨çé¿åº¦ï¼ä»¥åä½¿ç¨å¸¸æ°ç©ºé´çåæä¸è§£å³æ¬é¢ã

ç±äºæä»¬éè¦æ¾å°åæ°ç¬¬ *n* ä¸ªèç¹ï¼å æ­¤æä»¬å¯ä»¥ä½¿ç¨ä¸¤ä¸ªæé ![\textit{first} ](./p__textit{first}_.png)  å ![\textit{second} ](./p__textit{second}_.png)  åæ¶å¯¹é¾è¡¨è¿è¡éåï¼å¹¶ä¸ ![\textit{first} ](./p__textit{first}_.png)  æ¯ ![\textit{second} ](./p__textit{second}_.png)  è¶å *n* ä¸ªèç¹ãå½ ![\textit{first} ](./p__textit{first}_.png)  éåå°é¾è¡¨çæ«å°¾æ¶ï¼![\textit{second} ](./p__textit{second}_.png)  å°±æ°å¥½å¤äºåæ°ç¬¬ *n* ä¸ªèç¹ã

å·ä½å°ï¼åå§æ¶ ![\textit{first} ](./p__textit{first}_.png)  å ![\textit{second} ](./p__textit{second}_.png)  åæåå¤´èç¹ãæä»¬é¦åä½¿ç¨ ![\textit{first} ](./p__textit{first}_.png)  å¯¹é¾è¡¨è¿è¡éåï¼éåçæ¬¡æ°ä¸º *n*ãæ­¤æ¶ï¼![\textit{first} ](./p__textit{first}_.png)  å ![\textit{second} ](./p__textit{second}_.png)  ä¹é´é´éäº *n-1* ä¸ªèç¹ï¼å³ ![\textit{first} ](./p__textit{first}_.png)  æ¯ ![\textit{second} ](./p__textit{second}_.png)  è¶åäº *n* ä¸ªèç¹ã

å¨è¿ä¹åï¼æä»¬åæ¶ä½¿ç¨ ![\textit{first} ](./p__textit{first}_.png)  å ![\textit{second} ](./p__textit{second}_.png)  å¯¹é¾è¡¨è¿è¡éåãå½ ![\textit{first} ](./p__textit{first}_.png)  éåå°é¾è¡¨çæ«å°¾ï¼å³ ![\textit{first} ](./p__textit{first}_.png)  ä¸ºç©ºæéï¼æ¶ï¼![\textit{second} ](./p__textit{second}_.png)  æ°å¥½æååæ°ç¬¬ *n* ä¸ªèç¹ã

æ ¹æ®æ¹æ³ä¸åæ¹æ³äºï¼å¦ææä»¬è½å¤å¾å°çæ¯åæ°ç¬¬ *n* ä¸ªèç¹çåé©±èç¹èä¸æ¯åæ°ç¬¬ *n* ä¸ªèç¹çè¯ï¼å é¤æä½ä¼æ´å æ¹ä¾¿ãå æ­¤æä»¬å¯ä»¥èèå¨åå§æ¶å° ![\textit{second} ](./p__textit{second}_.png)  æååèç¹ï¼å¶ä½çæä½æ­¥éª¤ä¸åãè¿æ ·ä¸æ¥ï¼å½ ![\textit{first} ](./p__textit{first}_.png)  éåå°é¾è¡¨çæ«å°¾æ¶ï¼![\textit{second} ](./p__textit{second}_.png)  ç**ä¸ä¸ä¸ªèç¹**å°±æ¯æä»¬éè¦å é¤çèç¹ã

![p3](https://assets.leetcode-cn.com/solution-static/19/p3.png)

**ä»£ç **

```C++ [sol3-C++]
class Solution {
public:
    leetcode.editor.cn.base.ListNode* removeNthFromEnd(leetcode.editor.cn.base.ListNode* head, int n) {
        leetcode.editor.cn.base.ListNode* dummy = new leetcode.editor.cn.base.ListNode(0, head);
        leetcode.editor.cn.base.ListNode* first = head;
        leetcode.editor.cn.base.ListNode* second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first->next;
        }
        while (first) {
            first = first->next;
            second = second->next;
        }
        second->next = second->next->next;
        leetcode.editor.cn.base.ListNode* ans = dummy->next;
        delete dummy;
        return ans;
    }
};
```

```Java [sol3-Java]
class Solution {
    public leetcode.editor.cn.base.ListNode removeNthFromEnd(leetcode.editor.cn.base.ListNode head, int n) {
        leetcode.editor.cn.base.ListNode dummy = new leetcode.editor.cn.base.ListNode(0, head);
        leetcode.editor.cn.base.ListNode first = head;
        leetcode.editor.cn.base.ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        leetcode.editor.cn.base.ListNode ans = dummy.next;
        return ans;
    }
}
```

```Python [sol3-Python3]
class Solution:
    def removeNthFromEnd(self, head: leetcode.editor.cn.base.ListNode, n: int) -> leetcode.editor.cn.base.ListNode:
        dummy = leetcode.editor.cn.base.ListNode(0, head)
        first = head
        second = dummy
        for i in range(n):
            first = first.next

        while first:
            first = first.next
            second = second.next
        
        second.next = second.next.next
        return dummy.next
```

```Golang [sol3-Golang]
func removeNthFromEnd(head *leetcode.editor.cn.base.ListNode, n int) *leetcode.editor.cn.base.ListNode {
    dummy := &leetcode.editor.cn.base.ListNode{0, head}
    first, second := head, dummy
    for i := 0; i < n; i++ {
        first = first.Next
    }
    for ; first != nil; first = first.Next {
        second = second.Next
    }
    second.Next = second.Next.Next
    return dummy.Next
}
```

```C [sol3-C]
struct leetcode.editor.cn.base.ListNode* removeNthFromEnd(struct leetcode.editor.cn.base.ListNode* head, int n) {
    struct leetcode.editor.cn.base.ListNode* dummy = malloc(sizeof(struct leetcode.editor.cn.base.ListNode));
    dummy->val = 0, dummy->next = head;
    struct leetcode.editor.cn.base.ListNode* first = head;
    struct leetcode.editor.cn.base.ListNode* second = dummy;
    for (int i = 0; i < n; ++i) {
        first = first->next;
    }
    while (first) {
        first = first->next;
        second = second->next;
    }
    second->next = second->next->next;
    struct leetcode.editor.cn.base.ListNode* ans = dummy->next;
    free(dummy);
    return ans;
}
```

**å¤æåº¦åæ**

- æ¶é´å¤æåº¦ï¼*O(L)*ï¼å¶ä¸­ *L* æ¯é¾è¡¨çé¿åº¦ã

- ç©ºé´å¤æåº¦ï¼*O(1)*ã