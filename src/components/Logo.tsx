import React from 'react';

interface LogoProps {
  size?: 'sm' | 'md' | 'lg';
  showSlogan?: boolean;
  isDark?: boolean;
}

export const Logo: React.FC<LogoProps> = ({ size = 'md', showSlogan = true, isDark = false }) => {
  const iconSizeClass = {
    sm: 'w-8 h-8 text-lg',
    md: 'w-10 h-10 text-xl',
    lg: 'w-12 h-12 text-2xl',
  }[size];

  const titleSizeClass = {
    sm: 'text-base',
    md: 'text-lg',
    lg: 'text-xl',
  }[size];

  return (
    <div className="flex items-center gap-2.5">
      <div className={`${iconSizeClass} rounded-xl bg-gradient-to-br from-[#E07A5F] via-[#C25E38] to-[#A84F2E] flex items-center justify-center shadow-sm text-white select-none`}>
        🥟
      </div>
      <div>
        <div className={`font-extrabold ${titleSizeClass} leading-tight`}>
          <span className={isDark ? 'text-[#FDE68A]' : 'text-[#4A2810]'}>Lanche </span>
          <span className="text-[#C25E38]">Aconchego</span>
        </div>
        {showSlogan && (
          <p className={`text-[11px] font-medium leading-none mt-0.5 ${isDark ? 'text-[#FDE68A]/80' : 'text-[#8C5D3B]'}`}>
            O Sabor do Aconchego
          </p>
        )}
      </div>
    </div>
  );
};
