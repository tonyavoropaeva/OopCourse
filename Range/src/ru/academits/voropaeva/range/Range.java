package ru.academits.voropaeva.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && to >= number;
    }

    public Range getIntersection(Range range) {
        if (from < range.to && to > range.from) {
            return new Range(Math.max(from, range.from), Math.min(to, range.to));
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if (from <= range.to && to >= range.from) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        }

        return new Range[]{new Range(from, to), new Range(range.from, range.to)};
    }

    public Range[] getDifference(Range range) {
        if (from >= range.from && to <= range.to) {
            return new Range[0];
        }

        if (from < range.to && to > range.from) {
            if (from >= range.from) {
                return new Range[]{new Range(range.to, to)};
            }

            if (to <= range.to) {
                return new Range[]{new Range(from, range.from)};
            }

            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        return new Range[]{new Range(from, to)};
    }

    @Override
    public String toString() {
        return "(" + from + ", " + to + ")";
    }
}