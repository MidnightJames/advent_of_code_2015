package _2015.day_2;

public class Gift
{
    private int length;
    private int width;
    private int height;

    public Gift(int length, int width, int height)
    {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public int getRibbionArea()
    {
        int low = this.length;
        int mid;
        int high;

        if (this.width < low)
        {
            mid = low;
            low = this.width;
        }
        else
        {
            mid = this.width;
        }

        if (this.height < low)
        {
            high = mid;
            mid = low;
            low = this.height;
        }
        else if (this.height < mid)
        {
            high = mid;
            mid = this.height;
        }
        else
        {
            high = this.height;
        }

        int perimeter = 2 * low + 2 * mid;
        int volume = low * mid * high;

        return perimeter + volume;
    }

    public int getWrappingPaperArea()
    {
        int area = 0;
        int lowestSideArea = 0;
        int currentArea = 0;

        lowestSideArea = 2 * this.length * this.height;
        area = lowestSideArea;

        currentArea = 2 * this.length * this.width;
        area += currentArea;
        if (currentArea < lowestSideArea)
        {
            lowestSideArea = currentArea;
        }

        currentArea = 2 * this.height * this.width;
        area += currentArea;
        if (currentArea < lowestSideArea)
        {
            lowestSideArea = currentArea;
        }

        return area + (lowestSideArea / 2);
    }
}
