package Com.Univox.Core;
// Decompiled by Decafe PRO - Java Decompiler
// Classes: 1   Methods: 40   Fields: 17

public class Float
{

    private static final Float ERROR = new Float(0x8000000000000000L, 0x8000000000000000L);
    private static final int ITNUM = 5;
    public static final Float SQRT3 = new Float(0x18097b085e1246eeL, -18L);
    public static final Float PI;
    public static final Float ZERO = new Float();
    public static final Float ONE = new Float(1L);
    public static final Float E = new Float(0x3c5ba4ac6785680L, -17L);
    public static final Float LOG10 = new Float(0x1ff46cf1450516f4L, -18L);
    public static final Float PIdiv2;
    public static final Float PIdiv4;
    public static final Float PIdiv6;
    public static final Float PIdiv12;
    public static final Float PImul2;
    public static final Float PImul4;
    public long m_Val;
    public long m_E;
    private long maxLimit;

    public Float()
    {
        maxLimit = 0x147ae147ae147aeL;
        m_Val = m_E = 0L;
    }

    public Float(long l)
    {
        maxLimit = 0x147ae147ae147aeL;
        m_Val = l;
        m_E = 0L;
    }

    public Float(long l, long l1)
    {
        maxLimit = 0x147ae147ae147aeL;
        m_Val = l;
        if(m_Val == 0L)
            m_E = 0L;
        else
            m_E = l1;
    }

    public Float(Float float1)
    {
        maxLimit = 0x147ae147ae147aeL;
        m_Val = float1.m_Val;
        if(m_Val == 0L)
            m_E = 0L;
        else
            m_E = float1.m_E;
    }

    public long toLong()
    {
        long l = m_E;
        long l1 = m_Val;
        while(l != 0L) 
            if(l < 0L)
            {
                l1 /= 10L;
                l++;
            } else
            {
                l1 *= 10L;
                l--;
            }
        return l1;
    }

    public String toShortString()
    {
        if(isError())
            return "NaN";
        long l = m_E;
        long l1 = m_Val;
        Float float1 = this;
        if(l < 0L)
        {
            for(; l < 0L && l1 != 0L; l++)
                l1 /= 10L;

            l--;
            if(l > m_E)
            {
                long l2 = 1L;
                int i = m_Val >= 0L ? 1 : -1;
                for(long l3 = m_E; l3 < l;)
                {
                    l3++;
                    l2 *= 10L;
                }

                float1 = new Float((m_Val + ((long)i * l2) / 2L) / l2, l);
            }
        }
        return float1.toString();
    }

    public boolean isError()
    {
        return m_Val == ERROR.m_Val && m_E == ERROR.m_E;
    }

    public String toString()
    {
        if(isError())
            return "NaN";
        RemoveZero();
        String s = Long.toString(m_Val);
        int i = s.length();
        boolean flag = false;
        if(m_Val < 0L)
        {
            flag = true;
            s = s.substring(1, i);
            i--;
        }
        StringBuffer stringbuffer = new StringBuffer();
        if(m_E < 0L)
        {
            int j = (int)Math.abs(m_E);
            if(j < i)
            {
                stringbuffer.append(s.substring(0, i - j));
                stringbuffer.append(".");
                stringbuffer.append(s.substring(i - j));
            } else
            {
                stringbuffer.append(s);
                for(int l = 0; l < j - i; l++)
                    stringbuffer.insert(0, "0");

                stringbuffer.insert(0, "0.");
            }
        } else
        if((long)i + m_E > 6L)
        {
            stringbuffer.append(s.charAt(0));
            if(s.length() > 1)
            {
                stringbuffer.append(".");
                stringbuffer.append(s.substring(1));
            } else
            {
                stringbuffer.append(".0");
            }
            stringbuffer.append("E" + ((long)(i - 1) + m_E));
        } else
        {
            stringbuffer.append(s);
            for(int k = 0; (long)k < m_E; k++)
                stringbuffer.append("0");

        }
        s = stringbuffer.toString();
        stringbuffer = null;
        if(flag)
            s = "-" + s;
        return s;
    }

    public Float Add(Float float1)
    {
        if(float1.Equal(ZERO))
            return new Float(this);
        long l = m_E;
        long l1 = float1.m_E;
        long l2 = m_Val;
        long l3 = float1.m_Val;
        do
        {
            if(l == l1)
                break;
            if(l > l1)
            {
                if(Math.abs(l2) < maxLimit)
                {
                    l2 *= 10L;
                    l--;
                } else
                {
                    l3 /= 10L;
                    l1++;
                }
            } else
            if(l < l1)
                if(Math.abs(l3) < maxLimit)
                {
                    l3 *= 10L;
                    l1--;
                } else
                {
                    l2 /= 10L;
                    l++;
                }
        } while(true);
        if(l2 > 0L && l3 > 0x7fffffffffffffffL - l2 || l2 < 0L && l3 < 0x8000000000000000L - l2)
        {
            l2 /= 10L;
            l++;
            l3 /= 10L;
            l1++;
        }
        if(l2 > 0L && l3 > 0x7fffffffffffffffL - l2)
            return new Float(ERROR);
        if(l2 < 0L && l3 < 0x8000000000000000L - l2)
            return new Float(ERROR);
        else
            return new Float(l2 + l3, l);
    }

    public Float Sub(Float float1)
    {
        if(float1.Equal(ZERO))
            return new Float(m_Val, m_E);
        else
            return Add(new Float(-float1.m_Val, float1.m_E));
    }

    public Float Mul(long l)
    {
        return Mul(new Float(l, 0L));
    }

    public Float Mul(Float float1)
    {
        if(float1.Equal(ZERO) || Equal(ZERO))
            return new Float(ZERO);
        if(float1.Equal(ONE))
            return new Float(this);
        boolean flag = m_Val < 0L;
        if(flag)
            m_Val = -m_Val;
        boolean flag1 = float1.m_Val < 0L;
        if(flag1)
            float1.m_Val = -float1.m_Val;
        do
        {
            if(float1.m_Val > m_Val)
            {
                if(0x7fffffffffffffffL / m_Val >= float1.m_Val)
                    break;
                float1.m_Val /= 10L;
                float1.m_E++;
                continue;
            }
            if(0x7fffffffffffffffL / float1.m_Val >= m_Val)
                break;
            m_Val /= 10L;
            m_E++;
        } while(true);
        if(flag)
            m_Val = -m_Val;
        if(flag1)
            float1.m_Val = -float1.m_Val;
        long l = m_E + float1.m_E;
        long l1 = m_Val * float1.m_Val;
        return new Float(l1, l);
    }

    public Float Div(long l)
    {
        return Div(new Float(l, 0L));
    }

    public Float Div(Float float1)
    {
        if(float1.Equal(ONE))
            return new Float(this);
        long l = m_E;
        long l1 = float1.m_E;
        long l2 = float1.m_Val;
        if(l2 == 0L)
            return new Float(ERROR);
        long l3 = m_Val;
        if(l3 == 0L)
            return new Float(ZERO);
        long l4 = 0L;
        do
        {
            l4 += l3 / l2;
            l3 %= l2;
            if(l3 != 0L && Math.abs(l4) <= 0xcccccccccccccccL)
            {
                if(Math.abs(l3) > 0xcccccccccccccccL)
                {
                    l2 /= 10L;
                    l1++;
                } else
                {
                    l3 *= 10L;
                    l--;
                }
                l4 *= 10L;
            } else
            {
                Float float2 = new Float(l4, l - l1);
                float2.RemoveZero();
                return float2;
            }
        } while(true);
    }

    public void RemoveZero()
    {
        if(m_Val == 0L)
            return;
        while(m_Val % 10L == 0L) 
        {
            m_Val /= 10L;
            m_E++;
        }
    }

    public boolean Great(Float float1)
    {
        long l = m_E;
        long l1 = float1.m_E;
        long l2 = m_Val;
        long l3 = float1.m_Val;
        do
        {
            if(l == l1)
                break;
            if(l > l1)
            {
                if(Math.abs(l2) < maxLimit)
                {
                    l2 *= 10L;
                    l--;
                } else
                {
                    l3 /= 10L;
                    l1++;
                }
            } else
            if(l < l1)
                if(Math.abs(l3) < maxLimit)
                {
                    l3 *= 10L;
                    l1--;
                } else
                {
                    l2 /= 10L;
                    l++;
                }
        } while(true);
        return l2 > l3;
    }

    public boolean Less(long l)
    {
        return Less(new Float(l, 0L));
    }

    public boolean Less(Float float1)
    {
        long l = m_E;
        long l1 = float1.m_E;
        long l2 = m_Val;
        long l3 = float1.m_Val;
        do
        {
            if(l == l1)
                break;
            if(l > l1)
            {
                if(Math.abs(l2) < maxLimit)
                {
                    l2 *= 10L;
                    l--;
                } else
                {
                    l3 /= 10L;
                    l1++;
                }
            } else
            if(l < l1)
                if(Math.abs(l3) < maxLimit)
                {
                    l3 *= 10L;
                    l1--;
                } else
                {
                    l2 /= 10L;
                    l++;
                }
        } while(true);
        return l2 < l3;
    }

    public boolean Equal(Float float1)
    {
        long l = m_E;
        long l1 = float1.m_E;
        long l2 = m_Val;
        long l3 = float1.m_Val;
        if(l2 == 0L && l3 == 0L || l2 == l3 && l == l1)
            return true;
        if(Math.abs(l - l1) > 20L)
            return false;
        do
        {
            if(l == l1)
                break;
            if(l > l1)
            {
                if(Math.abs(l2) < maxLimit)
                {
                    l2 *= 10L;
                    l--;
                } else
                {
                    l3 /= 10L;
                    l1++;
                }
            } else
            if(l < l1)
                if(Math.abs(l3) < maxLimit)
                {
                    l3 *= 10L;
                    l1--;
                } else
                {
                    l2 /= 10L;
                    l++;
                }
        } while(true);
        return l2 == l3;
    }

    public Float Neg()
    {
        return new Float(-m_Val, m_E);
    }

    public static Float sin(Float float1)
    {
        for(; float1.Great(PI); float1 = float1.Sub(PImul2));
        for(; float1.Less(PI.Neg()); float1 = float1.Add(PImul2));
        Float float2 = float1.Mul(float1.Mul(float1));
        Float float3 = float2.Div(6L);
        Float float4 = float1.Mul(float1.Mul(float2));
        Float float5 = float4.Div(120L);
        Float float6 = float1.Mul(float1.Mul(float4));
        Float float7 = float6.Div(5040L);
        Float float8 = float1.Mul(float1.Mul(float6));
        Float float9 = float8.Div(0x58980L);
        Float float10 = float1.Mul(float1.Mul(float8));
        Float float11 = float10.Div(0x2611500L);
        Float float12 = float1.Sub(float3).Add(float5).Sub(float7).Add(float9).Sub(float11);
        if(float12.Less(new Float(0xfffffffffff0bdc1L, -6L)))
            return new Float(-1L);
        if(float12.Great(new Float(0xf423fL, -6L)))
            return new Float(1L);
        if(float12.Great(new Float(-5L, -4L)) && float12.Less(new Float(5L, -4L)))
            return new Float(0L);
        else
            return float12;
    }

    public static Float cos(Float float1)
    {
        for(; float1.Great(PI); float1 = float1.Sub(PImul2));
        for(; float1.Less(PI.Neg()); float1 = float1.Add(PImul2));
        Float float2 = float1.Mul(float1);
        Float float3 = float2.Div(2L);
        Float float4 = float2.Mul(float2);
        Float float5 = float4.Div(24L);
        Float float6 = float2.Mul(float4);
        Float float7 = float6.Div(720L);
        Float float8 = float4.Mul(float4);
        Float float9 = float8.Div(40320L);
        Float float10 = float8.Mul(float2);
        Float float11 = float10.Div(0x375f00L);
        Float float12 = ONE.Sub(float3).Add(float5).Sub(float7).Add(float9).Sub(float11);
        if(float12.Less(new Float(0xfffffffffff0bdc1L, -6L)))
            return ONE.Neg();
        if(float12.Great(new Float(0xf423fL, -6L)))
            return new Float(ONE);
        if(float12.Great(new Float(-5L, -4L)) && float12.Less(new Float(5L, -4L)))
            return new Float(ZERO);
        else
            return float12;
    }

    public static Float sqrt(Float float1)
    {
        int i = 0;
        boolean flag = false;
        if(float1.Less(ZERO))
            return new Float(ERROR);
        if(float1.Equal(ZERO))
            return new Float(ZERO);
        if(float1.Equal(ONE))
            return new Float(ONE);
        if(float1.Less(ONE))
        {
            float1 = ONE.Div(float1);
            flag = true;
        }
        long l = float1.m_E / 2L;
        Float float4;
        for(float4 = new Float(float1.m_Val, float1.m_E - l * 2L); float4.Great(new Float(16L)); float4 = float4.Div(16L))
            i++;

        Float float2 = new Float(2L);
        for(int j = 5; j > 0; j--)
        {
            Float float3 = float4.Div(float2);
            float2 = float2.Add(float3);
            float2 = float2.Div(2L);
        }

        while(i > 0) 
        {
            i--;
            float2 = float2.Mul(4L);
        }
        float2.m_E += l;
        if(flag)
            float2 = ONE.Div(float2);
        return float2;
    }

    public static Float tan(Float float1)
    {
        Float float2 = cos(float1);
        if(float2.Equal(ZERO))
            return new Float(ERROR);
        else
            return sin(float1).Div(float2);
    }

    public static Float parse(String s, int i)
        throws NumberFormatException
    {
        boolean flag = false;
        if(s.length() == 0)
            throw new NumberFormatException();
        if(s.charAt(0) == '-')
        {
            s = s.substring(1);
            flag = true;
        } else
        if(s.charAt(0) == '+')
            s = s.substring(1);
        if(s.length() == 0 || !Character.isDigit(s.charAt(0)))
            throw new NumberFormatException();
        int j = s.indexOf(".");
        if(j < 0)
            j = s.indexOf(44);
        long l = 0L;
        int k = s.indexOf(69);
        if(k == -1)
            k = s.indexOf(101);
        if(k != -1)
        {
            String s1 = new String(s.substring(k + 1));
            l = Long.parseLong(s1);
            s = s.substring(0, k);
        }
        if(j != -1)
        {
            if(k >= 0 && j >= k)
                throw new NumberFormatException();
            for(int i1 = j + 1; i1 < s.length(); i1++)
                if(Character.isDigit(s.charAt(i1)))
                    l--;
                else
                    throw new NumberFormatException();

            for(s = s.substring(0, j) + s.substring(j + 1); j > 1 && s.charAt(0) == '0'; j--)
                s = s.substring(1);

        }
        long l1 = 0L;
        int j1 = s.length();
        StringBuffer stringbuffer = new StringBuffer(s);
        do
        {
            for(; j1 > 20; j1--)
            {
                stringbuffer = stringbuffer.deleteCharAt(j1 - 1);
                if(j1 < j || j == -1)
                    l++;
            }

            try
            {
                l1 = Long.parseLong(stringbuffer.toString(), i);
                if(flag)
                    l1 = -l1;
                break;
            }
            catch(Exception exception)
            {
                stringbuffer = stringbuffer.deleteCharAt(j1 - 1);
                if(j1 < j || j == -1)
                    l++;
                j1--;
            }
        } while(true);
        stringbuffer = null;
        Float float1 = new Float(l1, l);
        float1.RemoveZero();
        return float1;
    }

    public static Float acos(Float float1)
    {
        return PIdiv2.Sub(asin(float1));
    }

    public static Float asin(Float float1)
    {
        if(float1.Less(ONE.Neg()) || float1.Great(ONE))
            return new Float(ERROR);
        if(float1.Equal(ONE.Neg()))
            return PIdiv2.Neg();
        if(float1.Equal(ONE))
            return PIdiv2;
        else
            return atan(float1.Div(sqrt(ONE.Sub(float1.Mul(float1)))));
    }

    public static Float atan(Float float1)
    {
        boolean flag = false;
        boolean flag1 = false;
        int i = 0;
        if(float1.Less(ZERO))
        {
            float1 = float1.Neg();
            flag = true;
        }
        if(float1.Great(ONE))
        {
            float1 = ONE.Div(float1);
            flag1 = true;
        }
        Float float3;
        for(; float1.Great(PIdiv12); float1 = float1.Mul(float3))
        {
            i++;
            float3 = float1.Add(SQRT3);
            float3 = ONE.Div(float3);
            float1 = float1.Mul(SQRT3);
            float1 = float1.Sub(ONE);
        }

        Float float2 = float1.Mul(float1);
        Float float4 = float2.Add(new Float(0xd6f684L, -7L));
        float4 = (new Float(0x3552cedL, -8L)).Div(float4);
        float4 = float4.Add(new Float(0x3984433L, -8L));
        float4 = float4.Sub(float2.Mul(new Float(0x4ebe06L, -8L)));
        float4 = float4.Mul(float1);
        for(; i > 0; i--)
            float4 = float4.Add(PIdiv6);

        if(flag1)
            float4 = PIdiv2.Sub(float4);
        if(flag)
            float4 = float4.Neg();
        return float4;
    }

    public static Float atan2(Float float1, Float float2)
    {
        if(float2.Equal(ZERO))
            return new Float(ERROR);
        Float float3 = atan(float1.Div(float2));
        if(float1.m_Val > 0L && float2.m_Val < 0L)
            float3 = float3.Add(PI);
        if(float1.m_Val < 0L && float2.m_Val < 0L)
            float3 = float3.Sub(PI);
        return float3;
    }

    public static Float exp(Float float1)
    {
        if(float1.Equal(ZERO))
            return new Float(ONE);
        Float float2 = new Float(ONE);
        long l = 1L;
        Float float3 = null;
        boolean flag = float1.Less(ZERO);
        if(flag)
            float1 = float1.Neg();
        float3 = (new Float(float1)).Div(l);
        for(long l1 = 2L; l1 < 50L; l1++)
        {
            float2 = float2.Add(float3);
            float3 = float3.Mul(float1).Div(l1);
        }

        if(flag)
            return ONE.Div(float2);
        else
            return float2;
    }

    private static Float _log(Float float1)
    {
        if(!float1.Great(ZERO))
            return new Float(ERROR);
        Float float2 = new Float(ZERO);
        Float float3 = float1.Sub(ONE);
        Float float4 = float1.Add(ONE);
        Float float5 = float3.Div(float4);
        Float float6 = new Float(float5);
        float4 = float6.Mul(float5);
        for(long l = 1L; l < 50L; l += 2L)
        {
            float2 = float2.Add(float6.Div(l));
            float6 = float6.Mul(float4);
        }

        return float2.Mul(2L);
    }

    public static Float log(Float float1)
    {
        if(!float1.Great(ZERO))
            return new Float(ERROR);
        boolean flag = false;
        Float float2 = _log(new Float(5L, -1L));
        if(float1.m_Val < 0L)
        {
            flag = true;
            float1.m_Val = -float1.m_Val;
        }
        int i;
        for(i = 0; float1.Great(ONE); i++)
            float1 = float1.Div(2L);

        Float float3 = _log(float1);
        for(int j = 0; j < i; j++)
            float3 = float3.Sub(float2);

        if(flag)
            return ONE.Div(float3);
        else
            return float3;
    }

    public static Float log10(Float float1)
    {
        if(!float1.Great(ZERO))
            return new Float(ERROR);
        boolean flag = false;
        Float float2 = _log(new Float(5L, -1L));
        if(float1.m_Val < 0L)
        {
            flag = true;
            float1.m_Val = -float1.m_Val;
        }
        int i = 0;
        if(float1.Great(ONE))
            while(float1.Great(ONE)) 
            {
                float1 = float1.Div(10L);
                i++;
            }
        else
            while(float1.Less(ONE)) 
            {
                float1 = float1.Mul(10L);
                i--;
            }
        Float float3 = new Float(i);
        if(!float1.Equal(ONE))
            float3 = float3.Add(log(float1).Div(LOG10));
        if(flag)
            return ONE.Div(float3);
        else
            return float3;
    }

    public static Float pow(Float float1, Float float2)
    {
        if(float1.Equal(ZERO))
            return new Float(ZERO);
        if(float1.Equal(ONE))
            return new Float(ONE);
        long l = float2.toLong();
        boolean flag = float2.Equal(new Float(l));
        if(flag)
        {
            boolean flag1 = false;
            if(float2.Less(0L))
                flag1 = true;
            Float float3 = new Float(float1);
            for(long l1 = 1L; l1 < (flag1 ? -l : l); l1++)
                float3 = float3.Mul(float1);

            if(flag1)
                return ONE.Div(float3);
            else
                return float3;
        }
        if(float1.Great(ZERO))
            return exp(float2.Mul(log(float1)));
        else
            return new Float(ERROR);
    }

    public static Float ceil(Float float1)
    {
        long l = float1.m_Val;
        if(float1.m_E < 0L)
        {
            long l1 = 1L;
            if(float1.m_E > -19L)
            {
                for(long l2 = 0L; l2 < -float1.m_E; l2++)
                    l1 *= 10L;

                l /= l1;
                l *= l1;
                if(float1.m_Val - l > 0L)
                    l += l1;
            } else
            if(l > 0L)
                return ONE;
            else
                return ZERO;
        }
        return new Float(l, float1.m_E);
    }

    public static Float floor(Float float1)
    {
        long l = float1.m_Val;
        if(float1.m_E < 0L)
        {
            long l1 = 1L;
            if(float1.m_E > -19L)
            {
                for(long l2 = 0L; l2 < -float1.m_E; l2++)
                    l1 *= 10L;

                l /= l1;
                l *= l1;
                if(float1.m_Val - l < 0L)
                    l -= l1;
            } else
            if(l < 0L)
                return ONE.Neg();
            else
                return ZERO;
        }
        return new Float(l, float1.m_E);
    }

    public static Float abs(Float float1)
    {
        if(float1.m_Val < 0L)
            return float1.Neg();
        else
            return new Float(float1);
    }

    public static Float Int(Float float1)
    {
        long l = float1.m_Val;
        if(float1.m_E < 0L)
        {
            long l1 = 1L;
            if(float1.m_E > -19L)
            {
                long l2 = 0L;
                for(long l3 = -float1.m_E; l2 < l3; l2++)
                    l1 *= 10L;

                l /= l1;
                l *= l1;
            } else
            {
                return ZERO;
            }
        }
        return new Float(l, float1.m_E);
    }

    public static Float Frac(Float float1)
    {
        long l = float1.m_Val;
        if(float1.m_E < 0L)
        {
            long l1 = 1L;
            if(float1.m_E > -19L)
            {
                long l2 = 0L;
                for(long l3 = -float1.m_E; l2 < l3; l2++)
                    l1 *= 10L;

                l /= l1;
                l *= l1;
                l = float1.m_Val - l;
            }
        } else
        {
            return ZERO;
        }
        return new Float(l, float1.m_E);
    }
    /**
   * Converts an angle measured in degrees to an approximately equivalent angle measured in radians. The conversion from degrees to radians is generally inexact
   * @param x Float - an angle, in degrees
   * @return Float - the measurement of the angle x in radians
   */
  static public Float toRadians(Float x)
  {
    return x.Mul(PI).Div(180L);
  }
  /**
   * Converts an angle measured in radians to an approximately equivalent angle measured in degrees. The conversion from radians to degrees is generally inexact; users should not expect cos(toRadians(90.0)) to exactly equal 0.0
   * @param x Float - an angle, in radians
   * @return Float - the measurement of the angle angrad in degrees
   */
  static public Float toDegrees(Float x)
  {
    return x.Mul(180L).Div(PI);
  }


    static 
    {
        PI = new Float(0x2b992ddfa23249d6L, -18L);
        PIdiv2 = PI.Div(2L);
        PIdiv4 = PIdiv2.Div(2L);
        PIdiv6 = PIdiv2.Div(3L);
        PIdiv12 = PIdiv6.Div(2L);
        PImul2 = PI.Mul(2L);
        PImul4 = PI.Mul(4L);
    }
}