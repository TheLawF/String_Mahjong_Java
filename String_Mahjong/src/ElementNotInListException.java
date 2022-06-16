/**
 * 这个异常类用以处理元素不在列表中的情况，通常在调用collection.contains()方法，
 * 或者使用 for循环对集合中的元素进行查找的时候使用。
 * <p>This Exception class is used for dealing with the case when an
 * element is not in list. Usually use it when evoking the method --
 * collection.contains(), or looking through the elements in collections
 * with "for loop".
 */
public class ElementNotInListException extends Exception{

    public ElementNotInListException()
    {
        super();
    }

    public ElementNotInListException(String message)
    {
        super(message);
    }

    public ElementNotInListException(String message, Throwable cause)
    {
        super(message,cause);
    }

    public ElementNotInListException(Throwable cause)
    {
        super(cause);
    }
}
