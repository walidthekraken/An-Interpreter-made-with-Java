
public abstract class FonctionsStandards extends Symbole {
    fctcodes code;

    public FonctionsStandards(String n, fctcodes code) {
        super(n.trim());
        this.code = code;
    }

    abstract public double getValue(double a) throws ExecutionExceptions;

    abstract public boolean estCorrecte() throws ExecutionExceptions;
}

class Sinus extends FonctionsStandards {

    public Sinus(String n, fctcodes code) {
        super(n, code);
    }

    @Override
    public double getValue(double a) throws ExecutionExceptions {
        return Math.sin(a);
    }

    @Override
    public boolean estCorrecte() throws ExecutionExceptions {
        return true;
    }
}

class Tan extends FonctionsStandards {
    public Tan(String n, fctcodes code) {
        super(n, code);
    }

    @Override
    public double getValue(double a) throws ExecutionExceptions {
        double result=Math.tan(a);
        if (result>Double.MAX_VALUE) throw new TanException();
        return result;
    }

    @Override
    public boolean estCorrecte() throws ExecutionExceptions {
        return true;
    }
}

class Log extends FonctionsStandards {
    public Log(String n, fctcodes code) {
        super(n, code);
    }

    @Override
    public double getValue(double a) throws ExecutionExceptions {
        if (a<=0) throw new LogarithmicException();
        return Math.log(a);
    }

    @Override
    public boolean estCorrecte() throws ExecutionExceptions {
        return true;
    }
}

class Abs extends FonctionsStandards {
    public Abs(String n, fctcodes code) {
        super(n, code);
    }

    @Override
    public double getValue(double a) throws ExecutionExceptions {
        return Math.abs(a);
    }

    @Override
    public boolean estCorrecte() throws ExecutionExceptions {
        return true;
    }
}

class Cosinus extends FonctionsStandards {
    public Cosinus(String n, fctcodes code) {
        super(n, code);
    }

    @Override
    public double getValue(double a) throws ExecutionExceptions {
        return Math.cos(a);
    }

    @Override
    public boolean estCorrecte() throws ExecutionExceptions {
        return true;
    }
}

class Sqrt extends FonctionsStandards {
    public Sqrt(String n, fctcodes code) {
        super(n, code);
    }

    @Override
    public double getValue(double a) throws ExecutionExceptions {
        if (a<0) throw new RootException();
        return Math.sqrt(a);
    }

    @Override
    public boolean estCorrecte() throws ExecutionExceptions {
        return true;
    }
}